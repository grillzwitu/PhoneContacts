package com.example.phonecontacts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.phonecontacts.databinding.ContactListItemBinding


class ContactAdapter: RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    inner class ContactViewHolder(private val binding: ContactListItemBinding):RecyclerView.ViewHolder(binding.root){
        fun  bindItem(contact: ContactList){
            binding.nameTextView.text = contact.name
            binding.numberTextView.text = contact.number
        }
    }

    private val contacts = mutableListOf<ContactList>()

    fun  setUpContacts(contacts: List<ContactList>) {
        this.contacts.addAll(contacts)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(ContactListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        holder.bindItem(contact)
    }

    override fun getItemCount(): Int {
        return  contacts.size
    }


}


//class ContactAdapter(val myContacts: ArrayList<ContactList>, val context: Context) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {
//
//    fun  setUpContacts(contacts: List<ContactList>) {
//        this.myContacts.addAll(contacts)
//        notifyDataSetChanged()
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
//        val  view = LayoutInflater.from(parent.context).inflate(R.layout.contact_list_item, parent, false)
//        return ContactViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
//        val contact = myContacts[position]
//        holder.bindItem(contact)
//    }
//
//    override fun getItemCount(): Int {
//        return  myContacts.size
//    }
//
//    inner class ContactViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
//        fun  bindItem(contact: ContactList){
//            val  contactName: TextView = itemView.findViewById(R.id.nameTextView)
//            val  contactNum: TextView = itemView.findViewById(R.id.numberTextView)
//
//            contactName.text = contact.name
//            contactNum.text = contact.number
//
//        }
//    }
//
//}