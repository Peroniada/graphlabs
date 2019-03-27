import edu.sperek.graphlab.GraphModel
import kotlin.Pair
import spock.lang.Specification

class GraphMachineSpec extends Specification {
    def edges
    GraphModel graphModel

    def setup() {
        edges = [new Pair(1,2), new Pair(2,3), new Pair(3,4), new Pair(1,4), new Pair(2,4)]
        graphModel = new GraphModel(4, 5, edges as ArrayList<Pair<Integer, Integer>>)
    }

    def 'Should return the number of verticies'() {
        given: "a graph"
        when: "user retrieves number of verts"
        int verts = graphModel.numberOfVertices
        then:
        verts == 4
    }

    def 'Should return list of vertices'() {
        given: "a graph"
        when: "user retrieves list of verices"
        def vertsList = graphModel.vertsList
        then:
        vertsList == [1,2,3,4]
    }

    def 'Should return the number of edges'() {
        given: "a graph"
        when: "user retrieves for number of edges"
        def numberOfEdges = graphModel.numberOfEdges
        then:
        numberOfEdges == 5
    }

    def 'Should return list of edges' () {
        given: "a graph"
        when: "user retrieves for list of edges"
        def edges = graphModel.edgesList
        then:
        edges == ["1-2", "2-3", "3-4", "1-4", "2-4"]
    }

    def 'Should return an adjacency matrix' () {
        given: "a graph"
        when: "user retrieves for adjacency matrix"
        def adjacencyMatrix = graphModel.adjacencyMatrix
        then:
        adjacencyMatrix == [[0,1,0,1][1,0,1,1][0,1,0,1][1,1,1,0]] as Integer[][]
    }
//      Jako użytkownik chcę mieć możliwość wypisania macierzy incydencji M

}
