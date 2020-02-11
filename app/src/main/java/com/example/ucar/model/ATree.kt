package com.example.ucar.model

class ATree {
    var plotID : String? = null
    var orderTree : Int? = null
    var dbh : Float? = null
    var grith : Float? = null
    var totalLenght : Float? = null
    var merchLenght : Float? = null
    var sawdustWeight : Float? = null
    var hbhNorth : Float? = null
    var hbhsouth : Float? = null
    var hbhEast : Float? = null
    var hbhWest : Float? = null

    constructor()
    constructor(
        plotID: String?,
        orderTree: Int?,
        dbh: Float?,
        grith: Float?,
        totalLenght: Float?,
        merchLenght: Float?,
        sawdustWeight: Float?,
        hbhNorth: Float?,
        hbhsouth: Float?,
        hbhEast: Float?,
        hbhWest: Float?
    ) {
        this.plotID = plotID
        this.orderTree = orderTree
        this.dbh = dbh
        this.grith = grith
        this.totalLenght = totalLenght
        this.merchLenght = merchLenght
        this.sawdustWeight = sawdustWeight
        this.hbhNorth = hbhNorth
        this.hbhsouth = hbhsouth
        this.hbhEast = hbhEast
        this.hbhWest = hbhWest
    }

    override fun toString(): String {
        return "ATree(plotID=$plotID, orderTree=$orderTree, dbh=$dbh, grith=$grith, totalLenght=$totalLenght, merchLenght=$merchLenght, sawdustWeight=$sawdustWeight, hbhNorth=$hbhNorth, hbhsouth=$hbhsouth, hbhEast=$hbhEast, hbhWest=$hbhWest)"
    }


}