import com.example.appexchance.forms.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class RestClient {
    companion object {
        private const val BASE_URL = "https://3.221.247.133"

        fun create(): ApiService {
            // Cria um TrustManager que aceita todos os certificados (apenas para desenvolvimento)
            val trustAllCertificates: Array<TrustManager> = arrayOf(
                object : X509TrustManager {
                    override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                    }

                    override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                    }

                    override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                        return arrayOf()
                    }
                }
            )

            // Configura o SSLContext para aceitar todos os certificados
            val sslContext = SSLContext.getInstance("TLS")
            sslContext.init(null, trustAllCertificates, java.security.SecureRandom())

            val sslSocketFactory = sslContext.socketFactory

            // Configura o OkHttpClient para aceitar todos os certificados e não verificar o hostname
            val okHttpClient = OkHttpClient.Builder()
                .sslSocketFactory(sslSocketFactory, trustAllCertificates[0] as X509TrustManager)
                .hostnameVerifier { _, _ -> true } // Permite todos os hostnames
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient) // Define o OkHttpClient personalizado
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}
