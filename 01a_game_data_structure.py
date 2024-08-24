# initialise game data array
def initialise_game_data(var_rows, var_cols):
    # initialise empty 2D array and return
    var_island_data = [[0 for _ in range(var_cols)] for _ in range(var_rows)]
    return var_island_data


# initialise bridge data
def initialise_bridge_list(var_size):
    var_list = [[0]]*var_size
    return var_list


# insert game data
def insert_game_data(var_island_data):
    # change all non-zero island numbers
    var_island_data[0][0] = 1
    var_island_data[0][3] = 3
    var_island_data[0][5] = 4
    var_island_data[1][1] = 1
    var_island_data[2][0] = 3
    var_island_data[2][5] = 2
    var_island_data[3][1] = 3
    var_island_data[3][3] = 4
    var_island_data[4][5] = 1
    var_island_data[5][0] = 4
    var_island_data[5][3] = 3
    var_island_data[7][1] = 2
    var_island_data[7][3] = 4
    var_island_data[7][5] = 2
    var_island_data[8][0] = 3
    var_island_data[8][2] = 3
    var_island_data[8][4] = 1
    return var_island_data


# print game data
def print_game_data(var_island_data):
    # get dimensions of grid
    var_rows = len(var_island_data)
    var_cols = len(var_island_data[0])
    for r in range(0, var_rows):
        for c in range(0, var_cols):
            # print each element
            print(var_island_data[r][c], end=" ")
        # add a newline at end of each row
        print("\n", end="")


# check if an island has all its bridges built
def check_island_bridges_complete(var_island_data, var_live_data, var_pos_row, var_pos_col):
    return var_island_data[var_pos_row][var_pos_col] == var_live_data[var_pos_row][var_pos_col]


# check if all islands' bridges are built
def check_all_island_bridges_complete(var_island_data, var_live_data):
    # get dimensions of grid
    var_rows = len(var_island_data)
    var_cols = len(var_island_data[0])
    # check each island
    for r in range(0, var_rows):
        for c in range(0, var_cols):
            # if any island hasn't had all its bridges built, return false
            if var_island_data[r][c] != var_live_data[r][c]:
                return False
    # if no island hasn't had all its bridges built (all islands have all their bridges built), return true
    return True


# set grid size
rows, cols = (9, 6)

# initialise 2D array and insert game data
island_data = insert_game_data(initialise_game_data(rows, cols))

# initialise live user data 2D array
live_data = initialise_game_data(rows, cols)

# initialise vertical bridges list
vert_brdiges = initialise_bridge_list(cols)

# initialise horizontal bridges list
hor_bridges = initialise_bridge_list(rows)

print("Game finished: {}".format(check_all_island_bridges_complete(island_data, live_data)))
