package datatypes

data class SearchSize(val size: Int, val page: Int) {
    constructor(size: Int) : this(size, 1)

    val params = mutableMapOf("size" to size.toString(), "page" to page.toString())
}