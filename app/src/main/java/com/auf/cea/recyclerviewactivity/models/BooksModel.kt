package com.auf.cea.recyclerviewactivity.models

data class BooksModel(
    var name: String,
    var author: String,
    var shortDescription: String,
    var description: String,
    var datePublished: String,
    var shopLink: String,
    var imgLink: Int
): java.io.Serializable
