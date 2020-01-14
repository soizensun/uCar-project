package com.example.ucar.model

class Plot {
    var id : Int? = null
    var plotID : String? = null
    var plotName : String? = null
    var ageYear : Int? = null
    var ageMonth : Int? = null
    var latitude : Float? = null
    var longitude : Float? = null
    var serviveRate : Float? = null
    var sampleTree : Int? = null

    constructor(
        plotID: String?,
        plotName: String?,
        ageYear: Int?,
        ageMonth: Int?,
        latitude: Float?,
        longitude: Float?,
        serviveRate: Float?,
        sampleTree: Int?
    ) {
        this.plotID = plotID
        this.plotName = plotName
        this.ageYear = ageYear
        this.ageMonth = ageMonth
        this.latitude = latitude
        this.longitude = longitude
        this.serviveRate = serviveRate
        this.sampleTree = sampleTree
    }
}