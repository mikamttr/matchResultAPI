package com.example.gamedayapi.beans

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "matches")
data class MatchBean(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var tournament: String,
    @Column(name = "local_team")
    var localTeam: String,
    @Column(name = "visitor_team")
    var visitorTeam: String,
    @Column(name = "local_team_score")
    var localTeamScore: Int = 0,
    @Column(name = "visitor_team_score")
    var visitorTeamScore: Int = 0,
    @Column(name = "match_date")
    var matchDate: LocalDateTime,
    var finished: Boolean = false
)
