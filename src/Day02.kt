fun main() {
    fun part1(input: List<String>): Int {

        var curr_depth=0
        var curr_horizontal_distance=0

        for(item in input)
        {
            val (direction,distance) = item.split(" ").map{value-> value}

            val intDistance = distance.toInt()

            when(direction)
            {
                "forward" -> curr_horizontal_distance+=intDistance
                "down" -> curr_depth+=intDistance
                "up"-> curr_depth-=intDistance
            }
        }
        return  curr_depth*curr_horizontal_distance


    }

    fun part2(input: List<String>): Int {

        var curr_depth=0
        var curr_horizontal_distance=0
        var aim=0
        for(item in input)
        {
            val (direction,distance) = item.split(" ").map{value-> value}

            val intDistance = distance.toInt()

            if(direction == "forward")
            {
                curr_horizontal_distance+=intDistance
                curr_depth+= aim*intDistance
            }
            else if(direction == "up")
            {
                aim-=intDistance
            }
            else
            {
                aim+=intDistance
            }
        }
        return  curr_depth*curr_horizontal_distance
    }

    val input = readInput("Day02")
    val test_input = readInput("Day02_test")

    println(part1(input))
    println(part2(input))
}
