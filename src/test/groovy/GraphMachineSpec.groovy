import UnitTest.UnitTest
import edu.sperek.graphlab.GraphModel
import spock.lang.Specification

@Category(UnitTest.class)
class GraphMachineSpec extends Specification {

//    • Jako użytkownik chcę mieć możliwość podania wierzchołków grafu
    def 'Should return the number of verticies'() {
        given:
        def edges = [[1, 2], [2, 3], [3, 4], [1, 4], [2, 4]]
        GraphModel = new GraphModel(4, 5, edges) as Class<GraphModel>
    }
//    • Jako użytkownik chce mieć możliwość zwrócenia zbioru wierzchołków
//    • Jako użytkownik chcę mieć możliwość obliczenia liczby krawędzi
//    • Jako użytkownik chcę mieć możliwość zwrócenia zbiory krawędzi E
//    • Jako użytkownik chcę mieć możliwość wypisania macierzy sąsiedztwa A
//      Jako użytkownik chcę mieć możliwość wypisania macierzy incydencji M

}
