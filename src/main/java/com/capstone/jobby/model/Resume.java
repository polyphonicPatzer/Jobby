package com.capstone.jobby.model;

import javax.persistence.*;

@Entity
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private byte[] bytes;

    private String description;

    @OneToOne
    @JoinColumn(name="candidateId")
    private Candidate candidate;

    private String hash;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public byte[] getBytes() { return bytes; }

    public void setBytes(byte[] bytes) { this.bytes = bytes; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public Candidate getCandidate() { return candidate; }

    public void setCandidate(Candidate candidate) { this.candidate = candidate; }

    public String getHash() { return hash;}

    public void setHash(String hash) { this.hash = hash; }
}
