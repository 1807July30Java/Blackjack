import { Player } from "./player";

export class Card {
    id: number;
    suit: string;
    val: string;
    playerHand: Player;
    room: number;
}