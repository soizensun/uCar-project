package com.example.ucar

class Plot {
    var id : Int? = null
    var plotID : String? = null
    var plotName : String? = null
    var ageYear : Int? = null
    var ageMonth : Int? = null
    var latitude : Int? = null
    var longitude : Int? = null
    var serviveRate : Int? = null
    var sampleTree : Int? = null

    constructor(
        plotID: String?,
        plotName: String?,
        ageYear: Int?,
        ageMonth: Int?,
        latitude: Int?,
        longitude: Int?,
        serviveRate: Int?,
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