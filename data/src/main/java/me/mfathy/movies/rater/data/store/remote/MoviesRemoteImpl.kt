package me.mfathy.movies.rater.data.store.remote

import io.reactivex.Flowable
import me.mfathy.movies.rater.data.mapper.remote.RemoteEntityMapperImpl
import me.mfathy.movies.rater.data.model.MovieEntity
import me.mfathy.movies.rater.data.repository.MoviesRemote
import me.mfathy.movies.rater.data.store.remote.model.RemoteMovie
import javax.inject.Inject

open class MoviesRemoteImpl @Inject constructor(
    val mapper: RemoteEntityMapperImpl
) : MoviesRemote {
    override fun getMovies(): Flowable<MutableList<MovieEntity>> {
        val movieList = mutableListOf<RemoteMovie>()
        movieList.add(
            RemoteMovie(
                "4fede184312f912796000037",
                "http://ia.media-imdb.com/images/M/MV5BMjAyMTg0MjgwOV5BMl5BanBnXkFtZTcwNTEzODY4Mw@@._V1._SX94_SY140_.jpg",
                "The extraordinary story of three Rwandan kids who walk 3000 miles to the Soccer World Cup in South Africa...",
                6.2,
                "Africa United"
            )
        )
        movieList.add(
            RemoteMovie(
                "4fede186312f912796000038",
                "http://ia.media-imdb.com/images/M/MV5BMjAyNDcxNTk3NV5BMl5BanBnXkFtZTcwMjk4MDU2NA@@._V1._SX94_SY140_.jpg",
                "A young man is rocked by two announcements from his elderly father: that he has terminal cancer, and that he has a young male lover.",
                7.2,
                "Beginners"
            )
        )
        movieList.add(
            RemoteMovie(
                "4fede18a312f912796000039",
                "http://ia.media-imdb.com/images/M/MV5BMjIzMjY4MTk2M15BMl5BanBnXkFtZTcwNzQ3ODg3NQ@@._V1._SX94_SY140_.jpg",
                "A kid from the Midwest moves out to Hollywood in order to follow in his parents footsteps -- and become a porn star.",
                3.0,
                "Bucky Larson : Born to Be a Star"
            )
        )
        movieList.add(
            RemoteMovie(
                "4fede18d312f91279600003a",
                "http://ia.media-imdb.com/images/M/MV5BMTU1ODkyNDkwOV5BMl5BanBnXkFtZTcwNzI2MTcyMQ@@._V1._SX94_SY140_.jpg",
                "The story of what happens after a master thief achieves his last big score, when the FBI agent who promised he'd capture him is about to do just that.",
                6.2,
                "Coup d'éclat"
            )
        )
        movieList.add(
            RemoteMovie(
                "4fede193312f91279600003c",
                "http://ia.media-imdb.com/images/M/MV5BMjI2NTk5NjkzOV5BMl5BanBnXkFtZTcwODgwODg0NA@@._V1._SX94_SY140_.jpg",
                "This is not the way it was supposed to happen. Like every other morning, Christian Echeveria, huissier...",
                5.8,
                "Dernier étage, gauche, gauche"
            )
        )
        movieList.add(
            RemoteMovie(
                "4fede19c312f91279600003e",
                "http://ia.media-imdb.com/images/M/MV5BMjEzODQ0MzEwMl5BMl5BanBnXkFtZTcwNzI4MTgwNg@@._V1._SX94_SY140_.jpg",
                "An exiled detective is recruited to solve a series of mysterious deaths that threaten to delay the inauguration of Empress Wu.",
                6.6,
                "Détective Dee : le mystère de la flamme fantôme"
            )
        )
        movieList.add(
            RemoteMovie(
                "4fede19e312f91279600003f",
                "http://ia.media-imdb.com/images/M/MV5BMTIxMDUzNjU5NF5BMl5BanBnXkFtZTcwMDY1MTQyMQ@@._V1._SX94_SY140_.jpg",
                "A complete imbecile struggles to make it through life on his own, until a strange invention makes him unbelievably wealthy.",
                7.0,
                "Easy Money"
            )
        )
        movieList.add(
            RemoteMovie(
                "4fede1a1312f912796000040",
                "http://ia.media-imdb.com/images/M/MV5BMTM0ODk3MjM1MV5BMl5BanBnXkFtZTcwNzc1MDIwNA@@._V1._SX94_SY140_.jpg",
                "A look at the early years of boxer Irish Micky Ward and his brother who helped train him before going pro in the mid 1980s.",
                7.9,
                "Fighter"
            )
        )
        movieList.add(
            RemoteMovie(
                "4fede1a5312f912796000041",
                "http://ia.media-imdb.com/images/M/MV5BOTUxNTAzNDE1NF5BMl5BanBnXkFtZTcwMTE1NzYwNA@@._V1._SX94_SY140_.jpg",
                "The story of Giancarlo Siani, a journalist killed by the Neapolitan Mafia in 1985.",
                7.1,
                "Fortapàsc"
            )
        )
        movieList.add(
            RemoteMovie(
                "4fede1a9312f912796000042",
                "http://ia.media-imdb.com/images/M/MV5BMTY3ODU5NDQ4OV5BMl5BanBnXkFtZTcwOTMxMjg0Nw@@._V1._SX94_SY140_.jpg",
                "Gianni is a retiree who has become invisible to most everyone around him. In response, he tries his best to generate some kind of extracurricular love life.",
                5.9,
                "Gianni et les femmes"
            )
        )
        movieList.add(
            RemoteMovie(
                "4fede1ab312f912796000043",
                "http://ia.media-imdb.com/images/M/MV5BMjIwNTM4Njg2NF5BMl5BanBnXkFtZTcwNDQwMTAwNA@@._V1._SX94_SY140_.jpg",
                "A vengeful father escapes from hell and chases after the men who killed his daughter and kidnapped his granddaughter.",
                5.5,
                "Hell Driver"
            )
        )
        movieList.add(
            RemoteMovie(
                "4fede1af312f912796000044",
                "http://ia.media-imdb.com/images/M/MV5BMTY1NjE1NTE0MV5BMl5BanBnXkFtZTcwOTYzNjUzNA@@._V1._SX94_SY140_.jpg",
                "When his pregnant fiancee becomes the latest victim of a serial killer, a secret agent blurs the line between good and evil in his pursuit of revenge.",
                7.8,
                "J'ai rencontré le diable"
            )
        )
        movieList.add(
            RemoteMovie(
                "4fede1b1312f912796000045",
                "http://ia.media-imdb.com/images/M/MV5BMTMzNjE4NDU1NF5BMl5BanBnXkFtZTcwMTAwNzQ0Mw@@._V1._SX94_SY140_.jpg",
                "In Brooklyn, a youth from an Orthodox Jewish community is lured into becoming an Ecstasy dealer by his pal who has ties to an Israeli drug cartel.",
                5.7,
                "Jewish Connection"
            )
        )
        movieList.add(
            RemoteMovie(
                "4fede661312f9127960002f0",
                "http://ia.media-imdb.com/images/M/MV5BMjA5MTYyMjc5M15BMl5BanBnXkFtZTcwMjI1OTQyMQ@@._V1._SX94_SY140_.jpg",
                "Based on a true story, Tod Lubitch is born with a deficient immune system (which is unlike being born with AIDS)...",
                5.6,
                "BUBBLE TROUBLE"
            )
        )
        movieList.add(
            RemoteMovie(
                "4fede667312f9127960002f2",
                "http://ia.media-imdb.com/images/M/MV5BMTc0MjA4MTY1NV5BMl5BanBnXkFtZTcwMDI3NzEzMQ@@._V1._SX94_SY140_.jpg",
                "In order to release his kidnapped sister, sports car mechanic Jackie Chan has to beat a super-criminal street racer.",
                6.1,
                "JACKIE CHAN SOUS PRESSION"
            )
        )
        movieList.add(
            RemoteMovie(
                "4fede66a312f9127960002f3",
                "http://ia.media-imdb.com/images/M/MV5BMTMwNTEyMTE1NV5BMl5BanBnXkFtZTcwNjI1NDA3NA@@._V1._SX94_SY140_.jpg",
                "When a solar eclipse sends a colossal blast of super chilled air towards the earth, it then sets off a catastrophic chain of events that threatens to engulf the world in ice and begin a new Ice Age.",
                3.7,
                "MENACE DE GLACE"
            )
        )


        return Flowable.just(movieList).map { movies ->
            movies.map { mapper.mapToEntity(it) }.toMutableList()
        }
    }
}