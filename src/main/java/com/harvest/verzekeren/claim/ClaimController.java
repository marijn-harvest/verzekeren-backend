package com.harvest.verzekeren.claim;

import com.harvest.verzekeren.user.MyUserPrincipal;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ClaimController
{
    private ClaimRepository claimRepository;

    private ModelMapper modelMapper;

    @Autowired
    public ClaimController(ClaimRepository claimRepository, ModelMapper modelMapper)
    {
        this.claimRepository = claimRepository;
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
}
