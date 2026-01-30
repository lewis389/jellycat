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

