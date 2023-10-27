package com.idn.doadzikir.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.idn.doadzikir.Adapter.DoadanDzikirAdapter
import com.idn.doadzikir.Model.DataDoaDzikir
import com.idn.doadzikir.R
import com.idn.doadzikir.databinding.ActivityQauliyahShalatBinding


class QauliyahShalatActivity : AppCompatActivity() {

    private var _binding : ActivityQauliyahShalatBinding? = null
    private val binding get() = _binding as ActivityQauliyahShalatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        _binding = ActivityQauliyahShalatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mAdapter = DoadanDzikirAdapter()
        mAdapter.setData(DataDoaDzikir.listDataQauliyah)
        binding.rvQauliyahShalat.adapter = mAdapter
        binding.rvQauliyahShalat.layoutManager = LinearLayoutManager(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}