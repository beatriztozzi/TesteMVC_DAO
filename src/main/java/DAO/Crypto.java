package DAO;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Scanner;

import static java.lang.System.*;

public class Crypto {

    private Crypto() {}
    public static final String AES = "AES"; //É A CRIPTOGRAFIA
    public static final String AES_GCM_NO_PADDING = "AES/GCM/NoPadding"; //tipo de da criptografia aes/GCK/NoPadding
    public static final int TAG_LENGTH = 128; //TAG PARA GERAR O IV DO GCM
    public static final int IV_LENGTH = 16; // TAMANHO DO IV
    public static final int KEY_LENGTH = 16; // TAMANHO DA CHAVE


    //IV É USADO PRA FAZER A CRIPTOGRAFIA (IV E CHAVE)

    public static void main(String[] args)
            throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException,
            BadPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
        out.println("");
        out.print("Insira alguma palavra/frase: ");
        Scanner scanner = new Scanner(System.in);

        SecretKey chaveSecreta = gerarChave();
        GCMParameterSpec iv = gerarIV();

        String encrypted = encriptarDado(scanner.nextLine(), chaveSecreta, iv);
        String decrypted = decriptarDado(encrypted, chaveSecreta, iv);
        out.println("");
        out.print("Dado encriptada: ");
        out.println(encrypted);
        out.print("Dado decriptada: ");
        out.println(decrypted);
    }

    public static SecretKey gerarChave(){
        byte[] bytes = new byte[KEY_LENGTH];
        new SecureRandom().nextBytes(bytes); ///SecureRandom --> PREENCHE O VETOR DE BYTES COM DADOS ALEATORIOS //NEXDTBYTE VERIFICA O TIPO DO ARRAY
        return new SecretKeySpec(bytes, AES);
    }

    public static GCMParameterSpec gerarIV() {
        byte[] bytes = new byte[IV_LENGTH];
        new SecureRandom().nextBytes(bytes);
        return new GCMParameterSpec(TAG_LENGTH, bytes);
    }

    //ELE ENCPRITA E DECRIPTA
    public static Cipher gerarCipher(int encryptMode, SecretKey key, GCMParameterSpec iv)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance(AES_GCM_NO_PADDING);
        cipher.init(encryptMode, key, iv);
        return cipher;
    }

    public static String encriptarDado(String message, SecretKey key, GCMParameterSpec iv)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        byte[] bytes = gerarCipher(Cipher.ENCRYPT_MODE, key, iv).doFinal(message.getBytes(StandardCharsets.UTF_8)); //  CHAMO GERAR CIPHER, E CHAMO O ENCRYPT MODE
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static String decriptarDado(String mensagem, SecretKey chave, GCMParameterSpec iv)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        byte[] bytes = gerarCipher(Cipher.DECRYPT_MODE, chave, iv).doFinal(Base64.getDecoder().decode(mensagem));
        return new String(bytes);
    }
}
