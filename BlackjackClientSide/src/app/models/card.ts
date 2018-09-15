import { Player } from "./player";

export class Card {
    id: number;
    suit: string;
    val: number;
    player: Player;
    room: number;
}
