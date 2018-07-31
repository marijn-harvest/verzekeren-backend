package com.harvest.verzekeren.claim;

import com.harvest.verzekeren.user.MyUserPrincipal;
import com.harvest.verzekeren.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ClaimController
{
    private ClaimRepository claimRepository;

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Autowired
    public ClaimController(ClaimRepository claimRepository, UserRepository userRepository, ModelMapper modelMapper)
    {
        this.claimRepository = claimRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/claims")
    public List<JsonClaim> getClaims(Authentication authentication)
    {
        MyUserPrincipal principal = (MyUserPrincipal) authentication.getPrincipal();

        List<Claim> claims = claimRepository.findByUserId(principal.getId());

        return claims.stream().map(claim -> modelMapper.map(claim, JsonClaim.class)).collect(Collectors.toList());
    }

    @DeleteMapping("/claim/{id}")
    public void deleteClaim(@PathVariable long id)
    {
        claimRepository.deleteById(id);
    }

    @PostMapping("/claim")
    public JsonClaim addClaim(Authentication authentication, @RequestBody JsonClaim jsonClaim)
    {
        MyUserPrincipal principal = (MyUserPrincipal) authentication.getPrincipal();

        Claim claim = modelMapper.map(jsonClaim, Claim.class);
        claim.setUser(userRepository.getOne(principal.getId()));

        claim = claimRepository.save(claim);
        return modelMapper.map(claim, JsonClaim.class);
    }
}
