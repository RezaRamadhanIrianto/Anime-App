package com.froyout.animesapp.core.utils

import com.froyout.animesapp.core.data.source.local.entity.AnimeEntity
import com.froyout.animesapp.core.data.source.remote.response.AnimeResponse
import com.froyout.animesapp.core.domain.models.Anime

object DataMapper {
    fun mapResponsesToDomain(input: List<AnimeResponse>) : List<Anime> =
        input.map {
            Anime(
                id = it.id,
                rank = it.rank,
                title = it.title,
                url = it.url,
                imageUrl = it.imageUrl,
                type = it.type,
                score = it.score,
                synopsis = it.synopsis,
                premiered = it.premiered,
                airing = it.airing
            )
        }

    fun mapEntitiesToDomain(input: List<AnimeEntity>): List<Anime> =
        input.map {
            Anime(
              id = it.id,
              rank = it.rank,
              title = it.title,
              url = it.url,
              imageUrl = it.imageUrl,
              type = it.type,
              score = it.score,
              synopsis = it.synopsis,
              premiered = it.premiered,
              airing = it.airing
            )
        }

    fun mapDomainToEntity(it: Anime): AnimeEntity =
        AnimeEntity(
            id = it.id,
            rank = it.rank,
            title = it.title,
            url = it.url,
            imageUrl = it.imageUrl,
            type = it.type,
            score = it.score,
            synopsis = it.synopsis,
            premiered = it.premiered,
            airing = it.airing
        )

    fun mapEntityToDomain(it: AnimeEntity?): Anime =
        Anime(
            id = it?.id ?: "",
            rank = it?.rank ?: "",
            title = it?.title ?: "",
            url = it?.url ?: "",
            imageUrl = it?.imageUrl ?: "",
            type = it?.type ?: "",
            score = it?.score ?: 0.0,
            synopsis = it?.synopsis,
            premiered = it?.premiered,
            airing = it?.airing
        )

    fun mapResponseToEntity(it: AnimeResponse) : AnimeEntity =
        AnimeEntity(
            id = it.id,
            rank = it.rank,
            title = it.title,
            url = it.url,
            imageUrl = it.imageUrl,
            type = it.type,
            score = it.score,
            synopsis = it.synopsis,
            premiered = it.premiered,
            airing = it.airing
        )
}