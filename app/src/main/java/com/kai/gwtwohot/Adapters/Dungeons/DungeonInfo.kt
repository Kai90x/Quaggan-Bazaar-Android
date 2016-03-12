package com.kai.gwtwohot.Adapters.Dungeons

import com.kai.gwtwohot.Dungeon


/**
 * Created by ikraammoothianpillay1 on 3/12/16.
 */
class DungeonInfo  {
    var isHeader: Boolean
    var dungeon: Dungeon

    constructor(dungeon: Dungeon,isHeader: Boolean = false) {
        this.dungeon = dungeon
        this.isHeader = isHeader
    }

}