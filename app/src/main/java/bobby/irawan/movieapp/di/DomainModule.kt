package bobby.irawan.movieapp.di

import bobby.irawan.movieapp.domain.RepositoryContract
import bobby.irawan.movieapp.domain.RepositoryImpl
import org.koin.dsl.module

/**
 * Created by bobbyirawan09 on 13/08/20.
 */

val domainModule = module {
    single<RepositoryContract> {
        RepositoryImpl(get())
    }
}