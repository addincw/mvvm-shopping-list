package com.addincendekia.mvvmshoppinglist

import android.app.Application
import com.addincendekia.mvvmshoppinglist.data.db.ShoppingDatabase
import com.addincendekia.mvvmshoppinglist.data.repositories.ShoppingItemRepository
import com.addincendekia.mvvmshoppinglist.ui.shopping.viewmodel.ShoppingItemFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class ShoppingApplication: Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@ShoppingApplication))
        bind() from singleton { ShoppingDatabase(instance()) }
        bind() from singleton { ShoppingItemRepository(instance()) }
        bind() from provider {
            ShoppingItemFactory(instance())
        }
    }
}