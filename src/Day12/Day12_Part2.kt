package Day12

import readInput


fun main() {


    fun dfs(graph:MutableMap<String,ArrayList<String>>, curr:String, ans:MutableList<Int>,
            visited:MutableSet<String>, twice:String, freq:Int=2)
    {
        if(curr=="end"){
            if(freq==2){
            ans[0]=ans[0]+1}
            return
        }

        if(curr==twice)
        {
            if(freq==2) return

            for(item in graph[curr]!!)
            {
                dfs(graph,item,ans,visited,twice,freq+1)
            }
            return
        }

        if(visited.contains(curr)) return
        if(curr[0] in 'a'..'z')
        {
            visited.add(curr)
        }

        for(item in graph[curr]!!)
        {
            if(!visited.contains(item)){
            dfs(graph,item,ans,visited,twice,freq)
            }
        }
        visited.remove(curr)

    }



    fun part2(input: List<String>): Int {
        var ans= mutableListOf<Int>(0)

        val graph: MutableMap<String,ArrayList<String>> = mutableMapOf()

        val smallName:MutableSet<String> = mutableSetOf()

        for(item in input)
        {
            val(a,b) = item.split("-")

            if(a[0] in 'a'..'z')
            {
                smallName.add(a)
            }

            if(b[0] in 'a'..'z')
            {
                smallName.add(b)
            }

            if(graph.containsKey(a))
            {
                graph[a]?.add(b)
            }
            else{
                graph[a] = arrayListOf(b)
            }

            if(graph.containsKey(b))
            {
                graph[b]?.add(a)
            }
            else{
                graph[b] = arrayListOf(a)
            }
        }

        val visited: MutableSet<String> = mutableSetOf()
        smallName.remove("start")
        smallName.remove("end")
        dfs(graph,"start",ans,visited,"")

        for(item in smallName) {
            dfs(graph, "start", ans, visited,item,0)
        }

        return ans[0]
    }


    val testInput = readInput("Day12/Day12_test")
    val input = readInput("Day12/Day12")

    println(part2(testInput))
    println(part2(input))
}