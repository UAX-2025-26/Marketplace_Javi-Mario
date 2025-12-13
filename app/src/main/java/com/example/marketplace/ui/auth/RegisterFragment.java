package com.example.marketplace.ui.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.marketplace.R;
import com.example.marketplace.databinding.FragmentRegisterBinding;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Fragment para el registro de nuevos usuarios con email y contraseña.
 */
public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding binding;
    private FirebaseAuth mAuth;
    private NavController navController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inicializar Firebase Auth.
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        // Listener para el botón de registro.
        binding.buttonRegister.setOnClickListener(v -> {
            String email = binding.editTextEmail.getText().toString().trim();
            String password = binding.editTextPassword.getText().toString().trim();

            // Validar campos antes de proceder.
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(getContext(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }
            registerUser(email, password);
        });
    }

    /**
     * Realiza la llamada a Firebase Auth para crear un nuevo usuario.
     * La operación es asíncrona.
     */
    private void registerUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), task -> {
                    if (task.isSuccessful()) {
                        // Si el registro funciona, notificar y volver a la pantalla de login.
                        Toast.makeText(getContext(), "Registro completado.", Toast.LENGTH_SHORT).show();
                        navController.navigateUp();
                    } else {
                        // En caso de error, mostrar el mensaje de Firebase.
                        Toast.makeText(getContext(), "Registro fallido: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    /**
     * Limpiar el binding cuando la vista se destruye para evitar memory leaks.
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
