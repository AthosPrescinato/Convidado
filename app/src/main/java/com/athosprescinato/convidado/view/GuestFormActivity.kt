package com.athosprescinato.convidado.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.athosprescinato.convidado.viewmodel.GuestFormViewModel
import com.athosprescinato.convidado.R
import com.athosprescinato.convidado.service.constants.GuestConstants
import kotlinx.android.synthetic.main.activity_guest_form.*

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mViewModel: GuestFormViewModel
    private var mGuestId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_form)

        mViewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        setListeners()
        observe()
        loadData()

        radio_presence.isChecked = true
    }

    override fun onClick(v: View) {
        val id = v.id
        if (id == R.id.button_save) {

            val name = edit_name.text.toString()
            val presence = radio_presence.isChecked

                mViewModel.save(mGuestId, name, presence)


        }
    }

    private fun loadData() {

        val bundle = intent.extras
        if (bundle != null) {
            mGuestId = bundle.getInt(GuestConstants.GUESTID)
            mViewModel.load(mGuestId)
        }

    }

    private fun observe() {
        mViewModel.saveGuest.observe(this, Observer {
            if (it) {
                Toast.makeText(applicationContext, "Sucesso ao salvar", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Falha ao salvar", Toast.LENGTH_SHORT).show()
            }
            finish()
        })

        mViewModel.guest.observe(this, Observer {
            edit_name.setText(it.name)
            if (it.presence) {
                radio_presence.isChecked = true
            } else {
                radio_absent.isChecked = true
            }
        })

    }

    private fun setListeners() {

        button_save.setOnClickListener(this)

    }
}
