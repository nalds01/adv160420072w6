package id.ac.ubaya.informatika.adv160420072week4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.fragment.app.findFragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import id.ac.ubaya.informatika.adv160420072week4.R
import id.ac.ubaya.informatika.adv160420072week4.util.loadImage
import id.ac.ubaya.informatika.adv160420072week4.viewmodel.DetailViewModel


class StudentDetailFragment : Fragment() {

private  lateinit var detailViewModel: DetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        if(arguments!=null){
            val studentID = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentID
            detailViewModel.fetch(studentID)
        }



        var studentPhoto = view.findViewById<ImageView>(R.id.studentPhoto)
        var progressBar2 = view.findViewById<ProgressBar>(R.id.progressBar2)
        var studentID = view.findViewById<TextInputEditText>(R.id.txtID)
        var studentName = view.findViewById<TextInputEditText>(R.id.txtName)
        var BOD = view.findViewById<TextInputEditText>(R.id.txtBOD)
        var phone = view.findViewById<TextInputEditText>(R.id.txtPhone)

        detailViewModel.studentLD.observe(viewLifecycleOwner){
            studentDetail->
            studentPhoto.loadImage(studentDetail.photoUrl.toString(),progressBar2)
            studentID.setText(studentDetail.id.toString())
            studentName.setText(studentDetail.name.toString())
            BOD.setText(studentDetail.bod.toString())
            phone.setText(studentDetail.phone.toString())
        }
    }

}