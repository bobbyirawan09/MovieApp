package bobby.irawan.movieapp.di

import bobby.irawan.movieapp.presentation.detail.MovieViewModel
import bobby.irawan.movieapp.presentation.nowplaying.NowPlayingViewModel
import bobby.irawan.movieapp.presentation.popular.PopularViewModel
import bobby.irawan.movieapp.presentation.toprated.TopRatedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by bobbyirawan09 on 13/08/20.
 */

val presentationModule = module {
    viewModel {
        PopularViewModel(get())
    }

    viewModel {
        NowPlayingViewModel(get())
    }

    viewModel {
        TopRatedViewModel(get())
    }

    viewModel {
        MovieViewModel(get())
    }
}