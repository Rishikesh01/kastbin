enum class Type(str: String) {
    /**
     * T e x t
     *
     * @constructor Create empty T e x t
     */

    TEXT("text"),

    /**
     * J a v a
     *
     * @constructor Create empty J a v a
     */
    JAVA("java"),

    /**
     * C p p
     *
     * @constructor Create empty C p p
     */
    CPP("cpp"),

    /**
     * R u s t
     *
     * @constructor Create empty R u s t
     */
    RUST("rust"),

    /**
     * G o
     *
     * @constructor Create empty G o
     */
    GO("go"),

    /**
     * K o t l i n
     *
     * @constructor Create empty K o t l i n
     */
    KOTLIN("kotlin"),

    /**
     * J s
     *
     * @constructor Create empty J s
     */
    JS("js"),

    /**
     * S c a l a
     *
     * @constructor Create empty S c a l a
     */
    SCALA("scala"),

    /**
     * E l i x i r
     *
     * @constructor Create empty E l i x i r
     */
    ELIXIR("elixir"),

    /**
     * P y t h o n
     *
     * @constructor Create empty P y t h o n
     */
    PYTHON("python");

    private val str: String = str
    override fun toString(): String {
        return str
    }
}