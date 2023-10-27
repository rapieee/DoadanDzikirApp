package com.idn.doadzikir

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.setMargins
import androidx.viewpager2.widget.ViewPager2
import com.idn.doadzikir.Adapter.ArtikelAdapter
import com.idn.doadzikir.Model.Artikel
import com.idn.doadzikir.UI.Detail.DetailArtikelActivity
import com.idn.doadzikir.UI.HarianDzikirDoaActivity
import com.idn.doadzikir.UI.PagiPetangDzikirActivity
import com.idn.doadzikir.UI.QauliyahShalatActivity
import com.idn.doadzikir.UI.SetiapSaatDzikirActivity
import com.idn.doadzikir.Utills.OnItemCallBack

class MainActivity : AppCompatActivity() {
    private var keep = true
    private val runner = Runnable { keep = false }

    private lateinit var vpArtikel: ViewPager2
    private val listArtikel: ArrayList<Artikel> = arrayListOf()

    private val slidingCallBack = object : ViewPager2.OnPageChangeCallback(){
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            for ( i in 0 until listArtikel.size ) {
                slideIndicator[i]?.setImageDrawable(
                    ContextCompat.getDrawable(applicationContext, R.drawable.dot_inactive)
                )
            }
            slideIndicator[position]?.setImageDrawable(
                ContextCompat.getDrawable(applicationContext,R.drawable.dot_active)
            )

        }
    }

    private lateinit var slideIndicator: Array<ImageView?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
            .setKeepOnScreenCondition { keep }
        Handler(Looper.getMainLooper())
        Handler().postDelayed(runner, 1000)
        setContentView(R.layout.activity_main)

        initData()
        initView()
        setupViewPager()

    }

    private fun setupViewPager() {
        val llSliderDots: LinearLayout = findViewById(R.id.ll_slider_dots)

        slideIndicator = arrayOfNulls(listArtikel.size)

        for (i in 0 until listArtikel.size) {
            slideIndicator[i] = ImageView(this)
            slideIndicator[i]?.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext, R.drawable.dot_inactive
                )
            )

            val param = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            param.setMargins(9,0,8,0)
            param.gravity = Gravity.CENTER_VERTICAL
            llSliderDots.addView(slideIndicator[i], param)
        }
        slideIndicator[0]?.setImageDrawable(
            ContextCompat.getDrawable(
                applicationContext,R.drawable.dot_active
            )
        )
    }

    private fun initData(){
        val judulArticle = resources.getStringArray(R.array.arr_title_artikel)
        val kontenArticle = resources.getStringArray(R.array.arr_desc_artikel)
        val imageArticle = resources.obtainTypedArray(R.array.arr_img_artikel)

      //  val listData = arrayListOf<Artikel>()
        for (i in judulArticle.indices){
            val data = Artikel(
                imageArticle.getResourceId(i,0),
                judulArticle[i],
                kontenArticle[i]
            )
            listArtikel.add(data)

        }
        imageArticle.recycle()

    }

    private fun initView() {
        val llDzikirDoaSholat: LinearLayout = findViewById(R.id.ll_dzikir_doa_shalat)
        llDzikirDoaSholat.setOnClickListener {
            startActivity(Intent(this, QauliyahShalatActivity::class.java))
        }


        val llDzikirSetiapSaat: LinearLayout = findViewById(R.id.ll_dzikir_setiap_saat)
        llDzikirSetiapSaat.setOnClickListener {
            startActivity(Intent(this, SetiapSaatDzikirActivity::class.java))
        }

        val llDzikirDoaHarian: LinearLayout = findViewById(R.id.ll_dzikir_doa_harian)
        llDzikirDoaHarian.setOnClickListener {
            startActivity(Intent(this, HarianDzikirDoaActivity::class.java))
        }

        val llDzikirpagiPetang: LinearLayout = findViewById(R.id.ll_dzikir_pagi_petang)
        llDzikirpagiPetang.setOnClickListener {
            startActivity(Intent(this, PagiPetangDzikirActivity::class.java))
        }

        vpArtikel = findViewById(R.id.vp_artikel)
        val mAdapter = ArtikelAdapter()
        mAdapter.setData(listArtikel)
        vpArtikel.adapter = mAdapter

        vpArtikel.registerOnPageChangeCallback(slidingCallBack)

        mAdapter.setOnItemClickCallback(object : OnItemCallBack {
            override fun onItemClicked(item: Artikel) {
                val intent = Intent(this@MainActivity, DetailArtikelActivity::class.java)
                intent.putExtra("data",item)
                startActivity(intent)
            }
        })

    }
}