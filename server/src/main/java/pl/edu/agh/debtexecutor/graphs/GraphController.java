package pl.edu.agh.debtexecutor.graphs;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.debtexecutor.graphs.dto.GraphDTO;

@RestController
@RequestMapping("api/v1/graphs")
public class GraphController {
    private final GraphService graphService;

    public GraphController(GraphService graphService) {
        this.graphService = graphService;
    }

    @GetMapping("/history")
    public @ResponseBody GraphDTO getHistoryGraph() {
        return GraphDTO.from(graphService.getHistoryGraph());
    }

    @GetMapping("/summary")
    public @ResponseBody GraphDTO getSummaryGraph() {
        return GraphDTO.from(graphService.getSummaryGraph());
    }

    @GetMapping("/simplified")
    public @ResponseBody GraphDTO getSimplifiedGraph() {
        return GraphDTO.from(graphService.getSimplifiedGraph());
    }
}
