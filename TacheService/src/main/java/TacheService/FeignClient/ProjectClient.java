package TacheService.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="project-service")
public interface ProjectClient {

    @GetMapping("projects/{id}/exist")
    boolean exists(@PathVariable("id") long id);
}
