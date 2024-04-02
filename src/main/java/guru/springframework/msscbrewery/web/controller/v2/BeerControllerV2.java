package guru.springframework.msscbrewery.web.controller.v2;

import guru.springframework.msscbrewery.services.v2.BeerServiceV2;
import guru.springframework.msscbrewery.web.model.v2.BeerDtoV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v2/beer")
@RestController
public class BeerControllerV2 {

    private final BeerServiceV2 beerService;

    @Autowired
    public BeerControllerV2(BeerServiceV2 beerService) {
        this.beerService = beerService;
    }

    @GetMapping({"/{beerId}"})
    public ResponseEntity<BeerDtoV2> getBeer(@PathVariable("beerId") UUID beerId){
        return ResponseEntity.ok(beerService.getBeerById(beerId));
    }

    @PostMapping
    public ResponseEntity<BeerDtoV2> handlePost(@RequestBody BeerDtoV2 beerDto) {
        BeerDtoV2 savedDto = beerService.saveBeer(beerDto);

        HttpHeaders headers = new HttpHeaders();
        // TODO: add host name to uri.
        headers.add("location", "/api/v1/beer/" + savedDto.getId().toString());


        return new ResponseEntity<BeerDtoV2>(savedDto, headers, HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<BeerDtoV2> handleUpdate(@PathVariable("beerId") UUID beerId,
                                                @RequestBody BeerDtoV2 beerDto) {
        beerService.updateBeer(beerId, beerDto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable("beerId") UUID beerId) {
        beerService.deleteById(beerId);
    }

}
