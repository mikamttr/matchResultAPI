package com.example.gamedayapi

import com.example.gamedayapi.beans.MatchBean
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/gamedayAPI")
class MatchRestController(val matchService: MatchService) {

    // http://localhost:8080/gamedayAPI/getAllMatches
    @GetMapping("/getAllMatches")
    fun getAllMatches(): List<MatchBean> {
        return matchService.getAllMatches()
    }

    // http://localhost:8080/gamedayAPI/getMatchById/{id}
    @GetMapping("/getMatchById/{id}")
    fun getMatchById(@PathVariable id: Long): ResponseEntity<MatchBean> {
        val match = matchService.getMatchById(id)
        return if (match.isPresent) {
            ResponseEntity.ok(match.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    // http://localhost:8080/gamedayAPI/getUpcomingMatches
    @GetMapping("/getUpcomingMatches")
    fun getUpcomingMatches(): List<MatchBean> {
        return matchService.getUpcomingMatches()
    }

    // http://localhost:8080/gamedayAPI/getMatchesInProgress
    @GetMapping("/getMatchesInProgress")
    fun getMatchesInProgress(): List<MatchBean> {
        return matchService.getMatchesInProgress()
    }

    // http://localhost:8080/gamedayAPI/getFinishedMatches
    @GetMapping("/getFinishedMatches")
    fun getFinishedMatches(): List<MatchBean> {
        return matchService.getFinishedMatches()
    }

    // http://localhost:8080/gamedayAPI/createMatch
    /* Json attendu :
     {
       "tournament": "Example",
       "localTeam": "Local Team Name",
       "visitorTeam": "Visitor Team Name",
       "localTeamScore": 0,
       "visitorTeamScore": 0,
       "matchDate": "2024-03-06T12:21:00",
       "finished": false
     }
    */
    @PostMapping("/createMatch")
    fun createMatch(@RequestBody matchBean: MatchBean): ResponseEntity<MatchBean> {
        val createdMatch = matchService.createMatch(matchBean)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMatch)
    }

    // http://localhost:8080/gamedayAPI/updateMatch
    /* Json attendu :
     {
        "id": 17,
        "tournament": "Example",
        "localTeam": "Local Team Name",
        "visitorTeam": "Visitor Team Name",
        "localTeamScore": 3,
        "visitorTeamScore": 2,
        "matchDate": "2024-03-06T12:21:00",
        "finished": true
    }
    */
    @PutMapping("/updateMatch")
    fun updateMatch(@RequestBody matchBean: MatchBean): ResponseEntity<MatchBean> {
        val updatedMatch = matchService.updateMatch(matchBean)
        return ResponseEntity.ok(updatedMatch)
    }

    // http://localhost:8080/gamedayAPI/deleteMatchById/{id}
    @DeleteMapping("/deleteMatchById/{id}")
    fun deleteMatchById(@PathVariable id: Long): ResponseEntity<Void> {
        matchService.deleteMatchById(id)
        return ResponseEntity.noContent().build()
    }
}