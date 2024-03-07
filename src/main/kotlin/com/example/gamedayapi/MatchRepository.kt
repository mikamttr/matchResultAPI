package com.example.gamedayapi

import com.example.gamedayapi.beans.MatchBean
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface MatchRepository : JpaRepository<MatchBean, Long> {
    fun findAllByMatchDateAfterOrderByMatchDateAsc(matchDate: LocalDateTime): List<MatchBean>

    fun findAllByMatchDateBeforeAndFinishedFalse(matchDate: LocalDateTime): List<MatchBean>

    fun findAllByFinishedTrue(): List<MatchBean>
}