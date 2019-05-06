package com.idiotleon.tutorialdagger2withkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.idiotleon.tutorialdagger2withkotlin.components.App
import com.idiotleon.tutorialdagger2withkotlin.essentials.Presenter
import com.idiotleon.tutorialdagger2withkotlin.extra.DEBUG_ENDPOINT
import com.idiotleon.tutorialdagger2withkotlin.modules.multibind.Multibinding
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    /*
    Requirements of field injection:
    1. it must be annotated with @Inject
    2. it must be vars
    3. it must be visible
    4. it must be (keyword)lateinit
    Now the (variable)presenter is set by dependency injection,
    not an explicit call to the factory
     */
    @Inject
    lateinit var presenter: Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.rootFactor.presenterFactory()
            .endpoint(DEBUG_ENDPOINT)
            .build()
            .inject(this)

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