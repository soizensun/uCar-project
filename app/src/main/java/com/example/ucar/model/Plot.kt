package com.example.ucar.model

class Plot {
//    var id : Int? = null
    var plotID : String? = null
    var ownerShip : String? = null
    var plotName : String? = null
    var clone : String? = null
    var plantType : String? = null
    var ageYear : Int? = null
    var ageMonth : Int? = null
    var spacing : String? = null
    var latitude : Float? = null
    var longitude : Float? = null
    var survivalRate : Float? = null
    var sampleTree : Int? = null
    var soilQuality : String? = null
    var sawType : String? = null

    constructor()
    constructor(
        plotID: String?,
        ownerShip: String?,
        plotName: String?,
        clone: String?,
        plantType : String?,
        ageYear: Int?,
        ageMonth: Int?,
        spacing : String?,
        latitude: Float?,
        longitude: Float?,
        survivalRate: Float?,
        sampleTree: Int?,
        soilQuality : String?,
        sawType : String?
    ) {
        this.plotID = plotID
        this.ownerShip = ownerShip
        this.plotName = plotName
        this.clone = clone
        this.plantType = plantType
        this.ageYear = ageYear
        this.ageMonth = ageMonth
        this.spacing = spacing
        this.latitude = latitude
        this.longitude = longitude
        this.survivalRate = survivalRate
        this.sampleTree = sampleTree
        this.soilQuality = soilQuality
        this.sawType = sawType

    }



}