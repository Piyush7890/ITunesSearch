package com.piyush.itunessearch.injection

import com.piyush.itunessearch.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules =[NetworkModule::class, RepoModule::class])
interface AppComponent
{
   fun inject(mainActivity: MainActivity)
}