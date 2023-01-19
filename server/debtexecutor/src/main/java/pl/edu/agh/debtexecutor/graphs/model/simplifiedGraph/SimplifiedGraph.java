package pl.edu.agh.debtexecutor.graphs.model.simplifiedGraph;

import pl.edu.agh.debtexecutor.expenses.model.Expense;
import pl.edu.agh.debtexecutor.graphs.model.Edge;
import pl.edu.agh.debtexecutor.graphs.model.Graph;
import pl.edu.agh.debtexecutor.graphs.model.Vertex;
import pl.edu.agh.debtexecutor.users.model.User;

import java.math.BigDecimal;
import java.util.*;

public class SimplifiedGraph implements Graph {
    Map<User, SimplifiedVertex> vertexes = new HashMap<>();

    public void addUser(User user) {
        vertexes.put(user, new SimplifiedVertex(user, new HashMap<>()));
    }

    public void addExpense(Expense expense) {
        SimplifiedVertex payerVertex = vertexes.get(expense.getPayer());
        SimplifiedVertex payeeVertex = vertexes.get(expense.getPayee());

        BigDecimal amount = edgeAlreadyExists(payerVertex, payeeVertex, expense.getAmount());
        if (!amount.equals(BigDecimal.ZERO)) {
            addEdge(payeeVertex, payeeVertex, amount);
        }
    }

    //ALGORYTM dodania krawędzi A --> B

    //KROK 0
    //Jeżeli taka krawędź już istnieje:
    //zwiększyc ją adekwatne
    //jeżeli istnieje krawędź przeciwna:
    //zmniejszyć ją lub usunąć i zmniejszyć wagę do dodania

    public BigDecimal edgeAlreadyExists(SimplifiedVertex vFrom, SimplifiedVertex vTo, BigDecimal amount){
        if (amount.equals(BigDecimal.ZERO)) {
            return BigDecimal.ZERO;
        }
        if (vFrom.edges().containsKey(vTo)) {
            vFrom.increaseEdge(vTo, amount);
            return BigDecimal.ZERO;
        }
        if (vTo.edges().containsKey(vFrom)) {
            BigDecimal currentEdgeValue = vTo.getEdgeValue(vFrom);
            if (currentEdgeValue.compareTo(amount) >= 0) {
                vTo.decreaseEdge(vFrom, amount);
                return BigDecimal.ZERO;
            } else {
                vTo.removeEdge(vFrom);
                return amount.subtract(currentEdgeValue);
            }
        }
        return amount;
    }

    //pobrać najniższą krawędź docelowego (B-->C)
        //jeżeli jej wartość jest większa od wartości dodawanej:
            //zmniejsz jej wartość o w. d.
            //dodaj krawędź A-->C o wartośći dodawaniej
        //jeżeli jej wartość e jest mniejsza od wartości dodawanej:
            //dodaj krawędź A-->C o wartośći e
            //usuń krawędź B--> C
            //dodać krawędź A-->B o wartości (w. d. - e) (zgodnie z algorytmem)
    //jeżeli B nie ma krawędzi wychodzących:
        //znajdź najmniejszą krawędź wchodzącą do A (D--> A)
            //jeżeli jej wartość jest mniejsza od wartości dodawanej:
                //zmniejsz jej wartość o w. d.
                //dodaj krawędź A-->C o wartośći dodawaniej
            //jeżeli jej wartość e jest większa od wartości dodawanej:
                //dodaj krawędź A-->C o wartośći e
                //usuń krawędź B--> C
                //dodać krawędź A-->B o wartości (w. d. - e) (zgodnie z algorytmem)

    public void addEdge(SimplifiedVertex A, SimplifiedVertex B, BigDecimal weight) {
        if (weight.equals(BigDecimal.ZERO)) return;

        Optional<SimplifiedEdge> lowestEdge = B.getLowestEdge();
        if (lowestEdge.isPresent()) {
            if (lowestEdge.get().value().compareTo(weight) >= 0) {
                B.decreaseEdge(lowestEdge.get().vTo(), weight);
                A.increaseEdge(lowestEdge.get().vTo(), weight);
            } else {
                B.removeEdge(lowestEdge.get().vTo());
                A.increaseEdge(lowestEdge.get().vTo(), lowestEdge.get().value());
                addEdge(A, B, weight.subtract(lowestEdge.get().value()));
            }
        } else {
            Optional<SimplifiedEdge> edge = getLowestEdgeTo(A);
            if (edge.isPresent()) {
                if (edge.get().value().compareTo(weight) < 0) {
                    B.decreaseEdge(edge.get().vTo(), weight);
                    A.increaseEdge(edge.get().vTo(), weight);
                } else {
                    B.removeEdge(edge.get().vTo());
                    A.increaseEdge(edge.get().vTo(), edge.get().value());
                    addEdge(A, B, weight.subtract(edge.get().value()));
                }
            } else {
                A.addEdge(B, weight);
            }
        }
    }

    public Optional<SimplifiedEdge> getLowestEdgeTo(SimplifiedVertex vertex) {
        return vertexes.values().stream()
                .flatMap(v -> Optional.ofNullable(v.edges().get(vertex)).stream())
                .min(new SimplifiedEdge.EdgeComparator());
    }
    @Override
    public List<Edge> getEdges() {
      return vertexes.values().stream()
                     .flatMap(v -> v.edges().values().stream())
                     .map(SimplifiedEdge::toEdge)
                     .toList();
    }

    @Override
    public List<Vertex> getVertices() {
        return vertexes.keySet().stream()
                       .map(Vertex::new)
                       .toList();
    }



}
