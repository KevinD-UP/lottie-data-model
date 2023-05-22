package io.kannelle.testkmp.android

data class Animation(
    val animationFamily: String,
    val animationFiles: List<String>,
    val animationRulesFiles: List<String>
)

fun mockAnimations() : List<Animation> {
    val animationsList : MutableList<Animation> = mutableListOf()


    var theme = "algiers"
    val pathToAlgiersRules = "rules/algiers"
    val algierList = listOf(
        "$pathToAlgiersRules/ALGIERS-FORD-1_1-rules.json",
        "$pathToAlgiersRules/ALGIERS-FORD-9_16-rules.json",
        "$pathToAlgiersRules/ALGIERS-FORD-rules.json",
        "$pathToAlgiersRules/ALGIERS-MINI-rules.json",
        "$pathToAlgiersRules/ALGIERS-PEUGEOT-1_1-rules.json",
        "$pathToAlgiersRules/ALGIERS-PEUGEOT-9_16-rules.json",
        "$pathToAlgiersRules/ALGIERS-PEUGEOT-rules.json",
        "$pathToAlgiersRules/ALGIERS-PLANE-1_1-rules.json",
        "$pathToAlgiersRules/ALGIERS-PLANE-9_16-rules.json",
        "$pathToAlgiersRules/ALGIERS-PLANE-rules.json",
        "$pathToAlgiersRules/ALGIERS-SIMCA-1_1-rules.json",
        "$pathToAlgiersRules/ALGIERS-SIMCA-9_16-rules.json",
        "$pathToAlgiersRules/ALGIERS-SIMCA-rules.json"
    )
    val algierFiles = listOf(
        "animations/$theme/a.json",
        "animations/$theme/b.json",
        "animations/$theme/c.json",
        "animations/$theme/c2.json",
        "animations/$theme/d.json",
        "animations/$theme/e.json",
        "animations/$theme/f.json",
        "animations/$theme/h.json",
        "animations/$theme/i.json",
        "animations/$theme/j.json",
        "animations/$theme/k.json",
        "animations/$theme/l.json",
        "animations/$theme/m.json",
        "animations/$theme/n.json",
        "animations/$theme/o.json",
        "animations/$theme/p.json",
        "animations/$theme/q.json",
        "animations/$theme/r.json",
        "animations/$theme/s.json",
        "animations/$theme/t.json",
    )
    animationsList.add(
        Animation(
            theme,
            algierFiles,
            algierList
        )
    )

    theme = "bali"
    val pathToBaliRules = "rules/bali"
    val baliList = listOf(
        "$pathToBaliRules/BALI-FORD-1_1-rules.json",
        "$pathToBaliRules/BALI-FORD-9_16-rules.json",
        "$pathToBaliRules/BALI-FORD-rules.json",
        "$pathToBaliRules/BALI-MINI-rules.json",
        "$pathToBaliRules/BALI-PEUGEOT-1_1-rules.json",
        "$pathToBaliRules/BALI-PEUGEOT-9_16-rules.json",
        "$pathToBaliRules/BALI-PEUGEOT-rules.json",
        "$pathToBaliRules/BALI-PLANE-1_1-rules.json",
        "$pathToBaliRules/BALI-PLANE-9_16-rules.json",
        "$pathToBaliRules/BALI-PLANE-rules.json"
    )
    val valiFiles = listOf(
        "animations/$theme/a.json",
        "animations/$theme/b.json",
        "animations/$theme/c.json",
        "animations/$theme/c2.json",
        "animations/$theme/d.json",
        "animations/$theme/e.json",
        "animations/$theme/f.json",
        "animations/$theme/h.json",
        "animations/$theme/i.json",
        "animations/$theme/j.json",
        "animations/$theme/k.json",
        "animations/$theme/l.json",
        "animations/$theme/m.json",
        "animations/$theme/n.json",
        "animations/$theme/o.json",
        "animations/$theme/p.json",
        "animations/$theme/q.json",
        "animations/$theme/r.json",
        "animations/$theme/s.json",
        "animations/$theme/t.json",
    )
    animationsList.add(
        Animation(
            theme,
            valiFiles,
            baliList
        )
    )

    theme = "berlin"
    val pathToBerlinRules = "rules/berlin"
    val berlinList = listOf(
        "$pathToBerlinRules/BERLIN-FORD-1_1-rules.json",
        "$pathToBerlinRules/BERLIN-FORD-9_16-rules.json",
        "$pathToBerlinRules/BERLIN-FORD-rules.json",
        "$pathToBerlinRules/BERLIN-MINI-rules.json",
        "$pathToBerlinRules/BERLIN-PEUGEOT-1_1-rules.json",
        "$pathToBerlinRules/BERLIN-PEUGEOT-9_16-rules.json",
        "$pathToBerlinRules/BERLIN-PEUGEOT-rules.json",
        "$pathToBerlinRules/BERLIN-PLANE-1_1-rules.json",
        "$pathToBerlinRules/BERLIN-PLANE-9_16-rules.json",
        "$pathToBerlinRules/BERLIN-PLANE-rules.json"
    )
    val berlinFiles = listOf(
        "animations/$theme/a.json",
        "animations/$theme/b.json",
        "animations/$theme/c.json",
        "animations/$theme/c2.json",
        "animations/$theme/d.json",
        "animations/$theme/e.json",
        "animations/$theme/f.json",
        "animations/$theme/h.json",
        "animations/$theme/i.json",
        "animations/$theme/j.json",
        "animations/$theme/k.json",
        "animations/$theme/l.json",
        "animations/$theme/m.json",
        "animations/$theme/n.json",
        "animations/$theme/o.json",
        "animations/$theme/p.json",
        "animations/$theme/q.json",
        "animations/$theme/r.json",
        "animations/$theme/s.json",
        "animations/$theme/t.json",
    )
    animationsList.add(
        Animation(
            theme,
            berlinFiles,
            berlinList
        )
    )
    theme = "bogota"
    val pathToBogotaRules = "rules/bogota"
    val bogotaList = listOf(
        "$pathToBogotaRules/BOGOTA-AUSTIN-1_1-rules.json",
        "$pathToBogotaRules/BOGOTA-AUSTIN-9_16-rules.json",
        "$pathToBogotaRules/BOGOTA-AUSTIN-rules.json",
        "$pathToBogotaRules/BOGOTA-AVENTI-1_1-rules.json",
        "$pathToBogotaRules/BOGOTA-AVENTI-9_16-rules.json",
        "$pathToBogotaRules/BOGOTA-AVENTI-rules.json",
        "$pathToBogotaRules/BOGOTA-DELOREAN-1_1-rules.json",
        "$pathToBogotaRules/BOGOTA-DELOREAN-9_16-rules.json",
        "$pathToBogotaRules/BOGOTA-DELOREAN-rules.json",
        "$pathToBogotaRules/BOGOTA-DODGE-1_1-rules.json",
        "$pathToBogotaRules/BOGOTA-DODGE-9_16-rules.json",
        "$pathToBogotaRules/BOGOTA-DODGE-rules.json",
        "$pathToBogotaRules/BOGOTA-LINCOLN-1_1-rules.json",
        "$pathToBogotaRules/BOGOTA-LINCOLN-9_16-rules.json",
        "$pathToBogotaRules/BOGOTA-LINCOLN-rules.json",
        "$pathToBogotaRules/BOGOTA-MCLAREN-1_1-rules.json",
        "$pathToBogotaRules/BOGOTA-MCLAREN-9_16-rules.json",
        "$pathToBogotaRules/BOGOTA-MCLAREN-rules.json",
        "$pathToBogotaRules/BOGOTA-MINI-rules.json",
        "$pathToBogotaRules/BOGOTA-RAM-1_1-rules.json",
        "$pathToBogotaRules/BOGOTA-RAM-9_16-rules.json",
        "$pathToBogotaRules/BOGOTA-RAM-rules.json",
        "$pathToBogotaRules/BOGOTA-ROUSH-1_1-rules.json",
        "$pathToBogotaRules/BOGOTA-ROUSH-9_16-rules.json",
        "$pathToBogotaRules/BOGOTA-ROUSH-rules.json",
        "$pathToBogotaRules/BOGOTA-TOYOTA-1_1-rules.json",
        "$pathToBogotaRules/BOGOTA-TOYOTA-9_16-rules.json",
        "$pathToBogotaRules/BOGOTA-TOYOTA-rules.json",
        "$pathToBogotaRules/BOGOTA-VECTOR-1_1-rules.json",
        "$pathToBogotaRules/BOGOTA-VECTOR-9_16-rules.json",
        "$pathToBogotaRules/BOGOTA-VECTOR-rules.json"
    )
    val bogotaFiles = listOf(
        "animations/$theme/a.json",
        "animations/$theme/b.json",
        "animations/$theme/c.json",
        "animations/$theme/c2.json",
        "animations/$theme/d.json",
        "animations/$theme/e.json",
        "animations/$theme/f.json",
        "animations/$theme/h.json",
        "animations/$theme/i.json",
        "animations/$theme/j.json",
        "animations/$theme/k.json",
        "animations/$theme/l.json",
        "animations/$theme/m.json",
        "animations/$theme/n.json",
        "animations/$theme/o.json",
        "animations/$theme/p.json",
        "animations/$theme/q.json",
        "animations/$theme/r.json",
        "animations/$theme/s.json",
        "animations/$theme/t.json",
    )
    animationsList.add(
        Animation(
            theme,
            bogotaFiles,
            bogotaList
        )
    )
    theme = "geneva"
    val pathToGenevaRules = "rules/geneva"
    val genevaList = listOf(
        "$pathToGenevaRules/GENEVA-FORD-1_1-rules.json",
        "$pathToGenevaRules/GENEVA-FORD-9_16-rules.json",
        "$pathToGenevaRules/GENEVA-FORD-rules.json",
        "$pathToGenevaRules/GENEVA-MINI-rules.json",
        "$pathToGenevaRules/GENEVA-PEUGEOT-1_1-rules.json",
        "$pathToGenevaRules/GENEVA-PEUGEOT-9_16-rules.json",
        "$pathToGenevaRules/GENEVA-PEUGEOT-rules.json",
        "$pathToGenevaRules/GENEVA-PLANE-1_1-rules.json",
        "$pathToGenevaRules/GENEVA-PLANE-9_16-rules.json",
        "$pathToGenevaRules/GENEVA-PLANE-rules.json"
    )
    val genevaFiles = listOf(
        "animations/$theme/a.json",
        "animations/$theme/b.json",
        "animations/$theme/c.json",
        "animations/$theme/c2.json",
        "animations/$theme/d.json",
        "animations/$theme/e.json",
        "animations/$theme/f.json",
        "animations/$theme/h.json",
        "animations/$theme/i.json",
        "animations/$theme/j.json",
        "animations/$theme/k.json",
        "animations/$theme/l.json",
        "animations/$theme/m.json",
        "animations/$theme/n.json",
        "animations/$theme/o.json",
        "animations/$theme/p.json",
        "animations/$theme/q.json",
        "animations/$theme/r.json",
        "animations/$theme/s.json",
        "animations/$theme/t.json",
    )
    animationsList.add(
        Animation(
            theme,
            genevaFiles,
            genevaList
        )
    )
    theme = "losAngeles"
    val pathToLosAngelesRules = "rules/losAngeles"
    val losAngelesList = listOf(
        "$pathToLosAngelesRules/LOS_ANGELES-FORD-1_1-rules.json",
        "$pathToLosAngelesRules/LOS_ANGELES-FORD-9_16-rules.json",
        "$pathToLosAngelesRules/LOS_ANGELES-FORD-rules.json",
        "$pathToLosAngelesRules/LOS_ANGELES-MINI-rules.json",
        "$pathToLosAngelesRules/LOS_ANGELES-PEUGEOT-1_1-rules.json",
        "$pathToLosAngelesRules/LOS_ANGELES-PEUGEOT-9_16-rules.json",
        "$pathToLosAngelesRules/LOS_ANGELES-PEUGEOT-rules.json",
        "$pathToLosAngelesRules/LOS_ANGELES-PLANE-1_1-rules.json",
        "$pathToLosAngelesRules/LOS_ANGELES-PLANE-9_16-rules.json",
        "$pathToLosAngelesRules/LOS_ANGELES-PLANE-9_16-rules.json",
        "$pathToLosAngelesRules/LOS_ANGELES-SIMCA-1_1-rules.json",
        "$pathToLosAngelesRules/LOS_ANGELES-SIMCA-9_16-rules.json",
        "$pathToLosAngelesRules/LOS_ANGELES-SIMCA-rules.json"
    )
    val losAngelesFiles = listOf(
        "animations/$theme/a.json",
        "animations/$theme/b.json",
        "animations/$theme/c.json",
        "animations/$theme/c2.json",
        "animations/$theme/d.json",
        "animations/$theme/e.json",
        "animations/$theme/f.json",
        "animations/$theme/h.json",
        "animations/$theme/i.json",
        "animations/$theme/j.json",
        "animations/$theme/k.json",
        "animations/$theme/l.json",
        "animations/$theme/m.json",
        "animations/$theme/n.json",
        "animations/$theme/o.json",
        "animations/$theme/p.json",
        "animations/$theme/q.json",
        "animations/$theme/r.json",
        "animations/$theme/s.json",
        "animations/$theme/t.json",
    )
    animationsList.add(
        Animation(
            theme,
            losAngelesFiles,
            losAngelesList
        )
    )
    theme = "paris"
    val pathToParisRules = "rules/paris"
    val parisList = listOf(
        "$pathToParisRules/PARIS-BUTTERFLY-1_1-rules.json",
        "$pathToParisRules/PARIS-BUTTERFLY-9_16-rules.json",
        "$pathToParisRules/PARIS-BUTTERFLY-rules.json",
        "$pathToParisRules/PARIS-FOX-1_1-rules.json",
        "$pathToParisRules/PARIS-FOX-9_16-rules.json",
        "$pathToParisRules/PARIS-FOX-rules.json",
        "$pathToParisRules/PARIS-MINI-rules.json",
        "$pathToParisRules/PARIS-OWL-1_1-rules.json",
        "$pathToParisRules/PARIS-OWL-9_16-rules.json",
        "$pathToParisRules/PARIS-OWL-9_16-rules.json"
    )
    val parisFiles = listOf(
        "animations/$theme/a.json",
        "animations/$theme/b.json",
        "animations/$theme/c.json",
        "animations/$theme/c2.json",
        "animations/$theme/d.json",
        "animations/$theme/e.json",
        "animations/$theme/f.json",
        "animations/$theme/h.json",
        "animations/$theme/i.json",
        "animations/$theme/j.json",
        "animations/$theme/k.json",
        "animations/$theme/l.json",
        "animations/$theme/m.json",
        "animations/$theme/n.json",
        "animations/$theme/o.json",
        "animations/$theme/p.json",
        "animations/$theme/q.json",
        "animations/$theme/r.json",
        "animations/$theme/s.json",
        "animations/$theme/t.json",
    )
    animationsList.add(
        Animation(
            theme,
            parisFiles,
            parisList
        )
    )
    theme = "seattle"
    val pathToSeattleRules = "rules/seattle"
    val seattleList = listOf(
        "$pathToSeattleRules/SEATTLE-FORD-1_1-rules.json",
        "$pathToSeattleRules/SEATTLE-FORD-9_16-rules.json",
        "$pathToSeattleRules/SEATTLE-FORD-rules.json",
        "$pathToSeattleRules/SEATTLE-MINI-rules.json",
        "$pathToSeattleRules/SEATTLE-PEUGEOT-1_1-rules.json",
        "$pathToSeattleRules/SEATTLE-PEUGEOT-9_16-rules.json",
        "$pathToSeattleRules/SEATTLE-PEUGEOT-rules.json",
        "$pathToSeattleRules/SEATTLE-PLANE-1_1-rules.json",
        "$pathToSeattleRules/SEATTLE-PLANE-9_16-rules.json",
        "$pathToSeattleRules/SEATTLE-PLANE-rules.json"
    )
    val seattleFiles = listOf(
        "animations/$theme/a.json",
        "animations/$theme/b.json",
        "animations/$theme/c.json",
        "animations/$theme/c2.json",
        "animations/$theme/d.json",
        "animations/$theme/e.json",
        "animations/$theme/f.json",
        "animations/$theme/h.json",
        "animations/$theme/i.json",
        "animations/$theme/j.json",
        "animations/$theme/k.json",
        "animations/$theme/l.json",
        "animations/$theme/m.json",
        "animations/$theme/n.json",
        "animations/$theme/o.json",
        "animations/$theme/p.json",
        "animations/$theme/q.json",
        "animations/$theme/r.json",
        "animations/$theme/s.json",
        "animations/$theme/t.json",
    )
    animationsList.add(
        Animation(
            theme,
            seattleFiles,
            seattleList
        )
    )

    return animationsList
}