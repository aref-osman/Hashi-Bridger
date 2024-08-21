# initialise game data array
def initialise_game_data(var_rows, var_cols):
    # initialise empty 2D array and return
    var_island_data = [[0 for _ in range(var_cols)] for _ in range(var_rows)]
    return var_island_data


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


# set grid size
rows, cols = (9, 6)

# initialise 2D array
island_data = initialise_game_data(rows, cols)
# island_data[3][3] = 4
# insert game data
island_data = insert_game_data(island_data)

print_game_data(island_data)
