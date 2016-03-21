package com.kai.gwtwohot.Dialogs

import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.kai.gwtwohot.Activities.MainActivity
import com.kai.gwtwohot.Fragments.SimpleFragment
import com.kai.gwtwohot.R
import com.kai.gwtwohot.Utils.ExceptionUtils
import com.kai.gwtwohot.Utils.GuildWarsUtils
import retrofit2.http.Query
import java.util.*

/**
 * Created by ikraammoothianpillay1 on 3/13/16.
 */
class ItemSearchDialog : DialogFragment() {

    private var edtSearchName: EditText? = null
    private var spnType: Spinner? = null
    private var spnOrderBy: Spinner? = null
    private var lblSubtype: TextView? = null
    private var spnSubtype: Spinner? = null
    private var spnRarity: Spinner? = null
    private var edtLevelMin: EditText? = null
    private var edtLevelMax: EditText? = null
    private var edtBuyPriceMin: EditText? = null
    private var edtBuyPriceMax: EditText? = null
    private var edtSellPriceMin: EditText? = null
    private var edtSellPriceMax: EditText? = null
    private var btnAdvanceSearch: Button? = null
    private var btnCancel: Button? = null
    private var onDialogResultListener: OnDialogResultListener? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_search, container, false)
        edtSearchName = rootView.findViewById(R.id.edtSearchName) as EditText
        spnType = rootView.findViewById(R.id.spnType) as Spinner
        spnOrderBy = rootView.findViewById(R.id.spnOrderBy) as Spinner
        lblSubtype = rootView.findViewById(R.id.lblSearchSubtype) as TextView
        spnSubtype = rootView.findViewById(R.id.spnSubtype) as Spinner
        spnRarity = rootView.findViewById(R.id.spnRarity) as Spinner
        edtLevelMin = rootView.findViewById(R.id.edtLevelMin) as EditText
        edtLevelMax = rootView.findViewById(R.id.edtLevelMax) as EditText
        edtBuyPriceMin = rootView.findViewById(R.id.edtBuyPriceMin) as EditText
        edtBuyPriceMax = rootView.findViewById(R.id.edtBuyPriceMax) as EditText
        edtSellPriceMin = rootView.findViewById(R.id.edtSellPriceMin) as EditText
        edtSellPriceMax = rootView.findViewById(R.id.edtSellPriceMax) as EditText
        btnAdvanceSearch = rootView.findViewById(R.id.btnSearch) as Button
        btnCancel = rootView.findViewById(R.id.btnCancel) as Button

        dialog.setTitle(R.string.search)

        try {
            onDialogResultListener = (activity as MainActivity).getCurrentFragment() as OnDialogResultListener
        } catch(e: Exception) {
            Log.e("Search Dialog",ExceptionUtils.getStackTrace(e))
        }

        initAdvanceSearch(savedInstanceState)


        return rootView
    }

    private fun initAdvanceSearch(savedInstanceState: Bundle?) {
        lblSubtype!!.visibility = View.GONE
        spnSubtype!!.visibility = View.GONE

        val res = resources
        var adapter = ArrayAdapter(activity, android.R.layout.simple_list_item_1, res.getStringArray(R.array.type_array))
        spnType?.adapter = adapter
        adapter = ArrayAdapter(activity, android.R.layout.simple_list_item_1, res.getStringArray(R.array.rarity_array))
        spnRarity?.adapter = adapter

        spnType?.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val type = spnType!!.selectedItem.toString()
                val subTypeArr = GuildWarsUtils.getItemSubtype(activity, type)

                if (subTypeArr != null && subTypeArr.size > 0) {
                    lblSubtype!!.visibility = View.VISIBLE
                    spnSubtype!!.visibility = View.VISIBLE
                    spnSubtype!!.setSelection(0)
                    val spinnerArrayAdapter = ArrayAdapter(activity, android.R.layout.simple_list_item_1, subTypeArr)
                    spnSubtype!!.adapter = spinnerArrayAdapter
                } else {
                    lblSubtype!!.visibility = View.GONE
                    spnSubtype!!.visibility = View.GONE
                    val allArr = resources.getStringArray(R.array.all_array)
                    val emptyAdapter = ArrayAdapter(activity, android.R.layout.simple_list_item_1, allArr)
                    spnSubtype!!.setSelection(0)
                    spnSubtype!!.adapter = emptyAdapter
                }
            }

        }

        btnAdvanceSearch?.setOnClickListener({
            var map = HashMap<String,String?>()
            map.put(NAME, if (edtSearchName?.text.toString().isNullOrEmpty()) null else edtSearchName?.text.toString())
            map.put(TYPE, if (spnType?.selectedItem.toString().equals(getString(R.string.item_all))) null else spnType?.selectedItem.toString())

            if (spnSubtype?.adapter != null && !spnSubtype!!.adapter.isEmpty)
            map.put(SUBTYPE, if (spnSubtype?.selectedItem.toString().equals(getString(R.string.item_all))) null else spnSubtype?.selectedItem.toString())

            map.put(RARITY, if (spnRarity?.selectedItem.toString().equals(getString(R.string.item_all))) null else spnRarity?.selectedItem.toString())
            map.put(MIN_LEVEL, if (edtLevelMin?.text.toString().isNullOrEmpty()) null else edtLevelMin?.text.toString())
            map.put(MAX_LEVEL, if (edtLevelMax?.text.toString().isNullOrEmpty()) null else edtLevelMax?.text.toString())
            map.put(MIN_BUY_PRICE, if (edtBuyPriceMin?.text.toString().isNullOrEmpty()) null else edtBuyPriceMin?.text.toString())
            map.put(MAX_BUY_PRICE, if (edtBuyPriceMax?.text.toString().isNullOrEmpty()) null else edtBuyPriceMax?.text.toString())
            map.put(MIN_SELL_PRICE, if (edtSellPriceMin?.text.toString().isNullOrEmpty()) null else edtSellPriceMin?.text.toString())
            map.put(MAX_SELL_PRICE, if (edtSellPriceMax?.text.toString().isNullOrEmpty()) null else edtSellPriceMax?.text.toString())
            val spinner_pos = spnOrderBy?.selectedItemPosition
            val orderby = resources.getStringArray(R.array.orderby_array_value)[spinner_pos!!]
            map.put(ORDER_BY, orderby)
            onDialogResultListener?.onPositiveResult(map)
            dialog.cancel()
        })
        btnCancel?.setOnClickListener({
            dialog.cancel()
        })

        if (savedInstanceState?.get(NAME) != null)
        edtSearchName?.setText(savedInstanceState?.get(NAME).toString())
        if (savedInstanceState?.get(MIN_LEVEL) != null)
        edtLevelMin?.setText(savedInstanceState?.get(MIN_LEVEL).toString())
        if (savedInstanceState?.get(MAX_LEVEL) != null)
        edtLevelMax?.setText(savedInstanceState?.get(MAX_LEVEL).toString())
        if (savedInstanceState?.get(MIN_BUY_PRICE) != null)
        edtBuyPriceMin?.setText(savedInstanceState?.get(MIN_BUY_PRICE).toString())
        if (savedInstanceState?.get(MAX_BUY_PRICE) != null)
        edtBuyPriceMax?.setText(savedInstanceState?.get(MAX_BUY_PRICE).toString())
        if (savedInstanceState?.get(MIN_SELL_PRICE) != null)
        edtSellPriceMin?.setText(savedInstanceState?.get(MIN_SELL_PRICE).toString())
        if (savedInstanceState?.get(MAX_SELL_PRICE) != null)
        edtSellPriceMax?.setText(savedInstanceState?.get(MAX_SELL_PRICE).toString())

        if (savedInstanceState?.get(TYPE) != null)
            spnType?.setSelection(res.getStringArray(R.array.type_array).indexOf(savedInstanceState?.get(TYPE).toString()))

        val subTypeArr = GuildWarsUtils.getItemSubtype(activity, spnType?.selectedItem.toString())
        if (savedInstanceState?.get(SUBTYPE) != null && subTypeArr != null) {
            spnSubtype?.setSelection(subTypeArr.indexOf(savedInstanceState?.get(SUBTYPE).toString()))
        }

        if (savedInstanceState?.get(RARITY) != null)
            spnRarity?.setSelection(res.getStringArray(R.array.rarity_array).indexOf(savedInstanceState?.get(RARITY).toString()))

        if (savedInstanceState?.get(ORDER_BY) != null) {
            val ascOrDesc = if (savedInstanceState?.get(ORDER_BY_ASC) != null) savedInstanceState?.get(ORDER_BY_ASC).toString() else "0"
            val orderBy = savedInstanceState?.get(ORDER_BY).toString().plus("-").plus(ascOrDesc)
            spnOrderBy?.setSelection(res.getStringArray(R.array.orderby_array_value).indexOf(orderBy))
        }
    }

    companion object {
        val NAME: String = "name"
        val TYPE: String = "type"
        val SUBTYPE: String = "subtype"
        val RARITY: String = "rarity"
        val MIN_LEVEL: String = "min_level"
        val MAX_LEVEL: String = "max_level"
        val MIN_BUY_PRICE: String = "min_buy_price"
        val MAX_BUY_PRICE: String = "max_buy_price"
        val MIN_SELL_PRICE: String = "min_sell_price"
        val MAX_SELL_PRICE: String = "max_sell_price"
        val ORDER_BY: String = "order_by"
        val ORDER_BY_ASC: String = "order_by_asc"
        fun newInstance(name: String? = null,
                        type: String? = null,
                        subtype: String? = null,
                        buyPriceMin: String? = null,
                        buyPriceMax: String? = null,
                        sellPriceMin: String? = null,
                        sellPriceMax: String? = null,
                        rarity: String? = null,
                        levelmin: String? = null,
                        levelmax: String? = null,
                        orderby: String? = null,
                        orderDesc: String? = null) : ItemSearchDialog {
            val itemSearchDialog = ItemSearchDialog()
            val bundle = Bundle()

            bundle.putString(NAME,name)
            bundle.putString(TYPE,type)
            bundle.putString(SUBTYPE,subtype)
            bundle.putString(RARITY,rarity)
            bundle.putString(MIN_LEVEL,levelmin)
            bundle.putString(MAX_LEVEL,levelmax)
            bundle.putString(MIN_BUY_PRICE,buyPriceMin)
            bundle.putString(MAX_BUY_PRICE,buyPriceMax)
            bundle.putString(MIN_SELL_PRICE,sellPriceMin)
            bundle.putString(MAX_SELL_PRICE,sellPriceMax)
            bundle.putString(ORDER_BY,orderby)
            bundle.putString(ORDER_BY_ASC,orderDesc)
            itemSearchDialog.arguments = bundle

            if (orderDesc != null)

            Log.e("ORDER",orderby)
            return itemSearchDialog
        }
    }
}