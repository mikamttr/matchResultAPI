package com.example.gamedayapi

import com.example.gamedayapi.beans.MatchBean
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*


@Service
class MatchService(val matchRepository: MatchRepository) {

    fun getAllMatches(): MutableList<MatchBean> = matchRepository.findAll()

    fun getMatchById(id: Long): Optional<MatchBean> = matchRepository.findById(id)

    fun createMatch(matchBean: MatchBean): MatchBean = matchRepository.save(matchBean)

    fun updateMatch(matchBean: MatchBean): MatchBean {
        if (matchBean.id == null || !matchRepository.existsById(matchBean.id!!)) {
            throw IllegalArgumentException("Match not found")
        }
        return matchRepository.save(matchBean)
    }

    fun deleteMatchById(id: Long) {
        matchRepository.deleteById(id)
    }

    fun getUpcomingMatches(): List<MatchBean> {
        val currentDateTime = LocalDateTime.now()
        return matchRepository.findAllByMatchDateAfterOrderByMatchDateAsc(currentDateTime)
    }

    fun getMatchesInProgress(): List<MatchBean> {
        val currentDateTime = LocalDateTime.now()
        return matchRepository.findAllByMatchDateBeforeAndFinishedFalse(currentDateTime)
    }

    fun getFinishedMatches(): List<MatchBean> {
        return matchRepository.findAllByFinishedTrue()
    }
}