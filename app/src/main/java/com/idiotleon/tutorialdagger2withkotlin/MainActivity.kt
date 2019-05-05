package com.idiotleon.tutorialdagger2withkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.idiotleon.tutorialdagger2withkotlin.basic.MainPresenterFactory
import com.idiotleon.tutorialdagger2withkotlin.essentials.DaggerPresenterFactory
import com.idiotleon.tutorialdagger2withkotlin.modules.multibind.Multibinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var presenter = MainPresenterFactory().get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerPresenterFactory.create().inject(this)

        setContentView(R.layout.activity_main)

        connect.setOnClickListener {
            presenter.connect { t -> textView.text = t }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.decorate -> textView.text = Multibinding.test(input.text.toString())
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }
}
