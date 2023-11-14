import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import androidx.fragment.app.DialogFragment
import com.example.einloggohnegoogle.R

class SplashFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val videoView: VideoView = view.findViewById(R.id.videoView)

        // Hier den Pfad zum Video setzen
        val videoPath = "android.resource://${requireActivity().packageName}/${R.raw.einfach_kochen_mit_klaus}"
        val uri = Uri.parse(videoPath)

        // VideoView konfigurieren
        videoView.setVideoURI(uri)
        videoView.setOnPreparedListener { mediaPlayer ->
            // Video fertig zum Abspielen
            mediaPlayer.isLooping = false // Wenn true, wird das Video wiederholt
            mediaPlayer.start()
        }

        // Beispiel: Verzögerung vor dem Schließen des Splash Screens
        view.postDelayed({
            dismiss()
            // Hier kannst du die nächste Aktion nach dem Splash Screen ausführen
        }, 4000) // 2000 Millisekunden (2 Sekunden) als Beispielverzögerung
    }
}