package com.example.phonecontacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.LinearLayout
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.phonecontacts.databinding.ActivityMainBinding
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter  = ContactAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpData(binding)
    }

    private fun setUpData(binding: ActivityMainBinding){

        val actionBar : ActionBar? = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.setDisplayShowHomeEnabled(true)


        val intent = intent
        val newName = intent.getStringExtra("iCatName")
        val  newCatLogo = intent.getIntExtra("icatLogo", 0)

        actionBar.title = newName

        binding.contactRecyclerView.adapter = adapter
        binding.contactRecyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))
        val builder = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.add_contact_dialog, null)
        builder.setView(view)

        val name = view.findViewById<TextInputEditText>(R.id.nameEdit)
        val num = view.findViewById<TextInputEditText>(R.id.numberEdit)
        val savBtn = view.findViewById<MaterialButton>(R.id.saveButton)

        num.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                savBtn.isEnabled = s?.length == 11
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        val alertDialog = builder.create()

        binding.floatingActionButton.setOnClickListener {
            alertDialog.show()
        }

        savBtn.setOnClickListener {
            val contact = ContactList(name.text.toString(), num.text.toString())
            val contacts = mutableListOf(contact)

            adapter.setUpContacts(contacts)

            alertDialog.dismiss()
        }
    }
}

//class MainActivity : AppCompatActivity() {
//
//    lateinit var adapter1 : ContactAdapter
//    private lateinit var recyclerView: RecyclerView
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        setUpData()
//        recyclerView.adapter = adapter1
//    }
//
//    private fun setUpData(){
//        val builder = AlertDialog.Builder(this)
//        val view = layoutInflater.inflate(R.layout.add_contact_dialog, null)
//        builder.setView(view)
//
//        val name = view.findViewById<TextInputEditText>(R.id.nameEdit)
//        val num = view.findViewById<TextInputEditText>(R.id.numberEdit)
//        val savBtn = view.findViewById<MaterialButton>(R.id.saveButton)
//        val addBtn = view.findViewById<FloatingActionButton>(R.id.floatingActionButton)
//
//        num.addTextChangedListener(object: TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                savBtn.isEnabled = s?.length == 11
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//            }
//        })
//
//        val alertDialog = builder.create()
//
//        addBtn.setOnClickListener {
//            alertDialog.show()
//        }
//
//        savBtn.setOnClickListener {
//            val contact = ContactList(name.text.toString(), num.text.toString())
//            val contacts = mutableListOf(contact)
//
//            adapter1.setUpContacts(contacts)
//
//            alertDialog.dismiss()
//        }
//    }
//}