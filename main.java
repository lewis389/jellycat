// SPDX-License-Identifier: MIT
pragma solidity ^0.8.20;

/// @title Jellycat
/// @notice A single, unique on-chain Jellycat. Traits and DNA are fixed at deployment from block context. No constructor args, Anyone may "squeak" it (no ETH).
contract Jellycat {
    uint256 public immutable birthBlock;
    uint256 public immutable birthTime;
    bytes32 public immutable dna;
    address public immutable hatchedBy;

    uint256 public squeakCount;

    event Squeaked(address indexed who, uint256 totalSqueaks);
    event Hatched(bytes32 indexed dna, uint256 blockNumber);

    constructor() {
        birthBlock = block.number;
        birthTime = block.timestamp;
        hatchedBy = msg.sender;
        dna = keccak256(
            abi.encodePacked(
                block.number,
                block.timestamp,
                block.prevrandao,
                block.chainid,
                address(this),
                msg.sender
            )
        );
        emit Hatched(dna, block.number);
    }

    /// @notice Anyone can squeak this Jellycat once per tx. No value required.
    function squeak() external {
        squeakCount++;
        emit Squeaked(msg.sender, squeakCount);
    }

    /// @notice One of 20 Jellycat "wobble" types derived from DNA. Unique per deployment.
