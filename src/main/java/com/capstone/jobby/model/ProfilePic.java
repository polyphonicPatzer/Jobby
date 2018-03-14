package com.capstone.jobby.model;

import javax.persistence.*;

@Entity
public class ProfilePic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private byte[] bytes;

    @OneToOne
    @JoinColumn(name="candidateId")
    private Candidate candidate;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public byte[] getBytes() { return bytes; }

    public void setBytes(byte[] bytes) { this.bytes = bytes; }

    public Candidate getCandidate() { return candidate; }

    public void setCandidate(Candidate candidate) { this.candidate = candidate; }
}
