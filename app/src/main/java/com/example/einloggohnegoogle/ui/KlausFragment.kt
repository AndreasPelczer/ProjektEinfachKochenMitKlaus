import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.einloggohnegoogle.R
import com.example.einloggohnegoogle.ui.DataFragment

class KlausFragment : Fragment() {
    private var isFragmentAttached = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        isFragmentAttached = true
    }

    override fun onDetach() {
        super.onDetach()
        isFragmentAttached = false
    }

    fun showFragment() {
        if (isFragmentAttached) {
            // Zeige das Fragment an
            val fragmentManager = requireActivity().supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerView, this)  // Hier verwenden wir "this" statt ein neues KlausFragment zu instantiieren
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Hier wird das Layout für das Fragment aufgebläht (inflated)
        return inflater.inflate(R.layout.fragment_klaus, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setze den Klicklistener auf das klausFragmentLayout
        view.findViewById<View>(R.id.klausFragmentLayout)?.setOnClickListener {
            // Wechsle zurück zum DataFragment
            val dataFragment = DataFragment()
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.fragmentContainerView, dataFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

}
