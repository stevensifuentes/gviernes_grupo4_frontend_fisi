package android.project.clinicapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.project.clinicapp.API.ClinicAPI;
import android.project.clinicapp.models.CitaProgramada;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.stripe.android.ApiResultCallback;
import com.stripe.android.PaymentIntentResult;
import com.stripe.android.Stripe;
import com.stripe.android.model.CardParams;
import com.stripe.android.model.ConfirmPaymentIntentParams;
import com.stripe.android.model.PaymentIntent;
import com.stripe.android.model.PaymentMethodCreateParams;
import com.stripe.android.view.CardFormView;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CardPayment extends AppCompatActivity {

    private static final String BACKEND_URL = "https://clinicauniversitaria.herokuapp.com/api/pagos/Intent";
    //private EditText amountText;
    private CardFormView cardFormView;
    private Button payButton;
    private ImageButton back;

    String especialidadFinal, fechaFinal, horaFinal;

    // we need paymentIntentClientSecret to start transaction
    private String paymentIntentClientSecret;
    //declare stripe
    private Stripe stripe;
    private Retrofit retrofit;
    Double amountDouble=null;

    private static final String TAG = "CITA PROGRAMADA";
    public static final String BASE_URL = "https://clinicauniversitaria.herokuapp.com/api/";

    private OkHttpClient httpClient;
    static ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_payment);
        // Elementos de la interface de pago
        //amountText = (EditText) findViewById(R.id.amount_id);
        cardFormView = (CardFormView) findViewById(R.id.cardFormView);
        payButton = (Button) findViewById(R.id.btnPagar);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Pago en progreso");
        progressDialog.setCancelable(false);
        httpClient = new OkHttpClient();

        back = findViewById(R.id.iconBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(v.getContext(), Menu.class);
                startActivity(inte);
            }
        });

        // Recuperar valores de la actividad de Programar cita

        especialidadFinal = getIntent().getStringExtra("especialidad");
        fechaFinal = getIntent().getStringExtra("fecha");
        horaFinal = getIntent().getStringExtra("hora");

        // Inicializamos librería retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder().serializeNulls().create()
                ))
                .build();

        //Initialize Stripe
        stripe = new Stripe(
                getApplicationContext(),
                Objects.requireNonNull("pk_test_51KMwfOLFRd7vEr3MUZw9H01MvZ4DUXKa4dm2sIAsPr8wMe3RU4z5HXuCekRcEidOEJqHWF7h1yWV8m94wPr5zpe2000Ib8mmue")
        );

        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Consultamos el monto
                amountDouble = 15.00;
                progressDialog.show();
                startCheckout();
                cardFormView.clearFocus();
            }
        });
    }

    private void startCheckout() {
        {
            // Create a PaymentIntent by calling the server's endpoint.
            MediaType mediaType = MediaType.get("application/json; charset=utf-8");
            double amount = amountDouble*100;

            Map<String,Object> payMap = new HashMap<>();
            payMap.put("currency","PEN");
            payMap.put("amount",amount);
            String json = new Gson().toJson(payMap);

            /*Map<String,Object> payMap = new HashMap<>();
            Map<String,Object> itemMap = new HashMap<>();
            List<Map<String,Object>> itemList = new ArrayList<>();
            payMap.put("currency","PEN");
            itemMap.put("id","photo_subscription");
            itemMap.put("amount",amount);
            itemList.add(itemMap);
            payMap.put("items",itemList);
            String json = new Gson().toJson(payMap);*/

            RequestBody body = RequestBody.create(json, mediaType);
            Request request = new Request.Builder()
                    .url(BACKEND_URL)
                    .post(body)
                    .build();
            httpClient.newCall(request)
                    .enqueue(new PayCallback(this));

        }
    }

    private static final class PayCallback implements Callback {
        @NonNull
        private final WeakReference<CardPayment> activityRef;
        PayCallback(@NonNull CardPayment activity) {
            activityRef = new WeakReference<>(activity);
        }
        @Override
        public void onFailure(@NonNull Call call, @NonNull IOException e) {
            progressDialog.dismiss();
            final CardPayment activity = activityRef.get();
            if (activity == null) {
                return;
            }
            activity.runOnUiThread(() ->
                    Toast.makeText(
                            activity, "Error: " + e.toString(), Toast.LENGTH_LONG
                    ).show()
            );
        }
        @Override
        public void onResponse(@NonNull Call call, @NonNull final Response response) throws IOException {
            final CardPayment activity = activityRef.get();
            if (activity == null) {
                return;
            }
            if (!response.isSuccessful()) {
                activity.runOnUiThread(() ->
                        Toast.makeText(
                                activity, "Error: " + response.toString(), Toast.LENGTH_LONG
                        ).show()
                );
            } else {
                activity.onPaymentSuccess(response);
            }
        }
    }

    private void onPaymentSuccess(@NonNull final Response response) throws IOException {
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String, String> responseMap = gson.fromJson(
                Objects.requireNonNull(response.body()).string(),
                type
        );
        paymentIntentClientSecret = responseMap.get("clientSecret");

        //once you get the payment client secret start transaction
        //get card detail
        //PaymentMethodCreateParams params = cardInputWidget.getPaymentMethodCreateParams();
        CardParams params = cardFormView.getCardParams();
        Log.i("TAG", "PARAMS: "+params);
        if (params != null) {
            //now use paymentIntentClientSecret to start transaction
            ConfirmPaymentIntentParams confirmParams = ConfirmPaymentIntentParams
                    .createWithPaymentMethodCreateParams(PaymentMethodCreateParams.createCard(params), paymentIntentClientSecret);
            //start payment
            stripe.confirmPayment(CardPayment.this, confirmParams);
        }
        Log.i("TAG", "onPaymentSuccess: "+paymentIntentClientSecret);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Handle the result of stripe.confirmPayment
        stripe.onPaymentResult(requestCode, data, new PaymentResultCallback(this));

    }

    private final class PaymentResultCallback implements ApiResultCallback<PaymentIntentResult> {
        @NonNull private final WeakReference<CardPayment> activityRef;
        PaymentResultCallback(@NonNull CardPayment activity) {
            activityRef = new WeakReference<>(activity);
        }
        //If Payment is successful
        @Override
        public void onSuccess(@NonNull PaymentIntentResult result) {
            progressDialog.dismiss();
            final CardPayment activity = activityRef.get();
            if (activity == null) {
                return;
            }
            PaymentIntent paymentIntent = result.getIntent();
            PaymentIntent.Status status = paymentIntent.getStatus();
            if (status == PaymentIntent.Status.Succeeded) {
                // Payment completed successfully
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                Toast toast =Toast.makeText(activity, "Pago realizado con éxito", Toast.LENGTH_SHORT);
                //toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            } else if (status == PaymentIntent.Status.RequiresPaymentMethod) {
                // Payment failed – allow retrying using a different payment method
                activity.displayAlert(
                        "Pago fallido",
                        Objects.requireNonNull(paymentIntent.getLastPaymentError()).getMessage()
                );
            }
        }
        //If Payment is not successful
        @Override
        public void onError(@NonNull Exception e) {
            progressDialog.dismiss();
            final CardPayment activity = activityRef.get();
            if (activity == null) {
                return;
            }
            // Payment request failed – allow retrying using the same payment method
            activity.displayAlert("Error", e.toString());
        }
    }
    private void displayAlert(@NonNull String title, @Nullable String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message);
        builder.setPositiveButton("Ok", null);
        builder.create().show();
    }

    private void sendAppointmentToAPI(String especialidad, String fecha, String hora, Integer historialId) {
        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();*/

        ClinicAPI service = retrofit.create(ClinicAPI.class);

        // Instanciamos la cita
        CitaProgramada cita = new CitaProgramada(especialidad, fecha, hora, historialId);

        retrofit2.Call<CitaProgramada> citaProgramadaCall = service.saveAppointments(cita);

        citaProgramadaCall.enqueue(new retrofit2.Callback<CitaProgramada>() {
            @Override
            public void onResponse(retrofit2.Call<CitaProgramada> call, retrofit2.Response<CitaProgramada> response) {
                Toast.makeText(CardPayment.this, "Su cita ha sido programada", Toast.LENGTH_SHORT).show();

                CitaProgramada responseFromAPI = response.body();
                String responseString = "Response Code : " + response.code()
                        + "\nEspecialidad : " + responseFromAPI.getEspecialidad()
                        + "\n" + "Fecha : " + responseFromAPI.getFecha()
                        + "\n" + "Hora : " + responseFromAPI.getHora()
                        + "\n" + "Historial id : " + responseFromAPI.getHistorial_id();

                Log.i(TAG, responseString);
            }

            @Override
            public void onFailure(retrofit2.Call<CitaProgramada> call, Throwable t) {
                Log.e(TAG, " Error, no se pudo guardar la cita: "+ t.getMessage());
            }
        });
    }
}