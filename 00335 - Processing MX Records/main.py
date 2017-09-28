import sys

class Node:
    def __init__(self, parent, value):
        self.value = value
        self.parent = parent
        self.values = []
    
    def __repr__(self):
        return stf(self.value)

class BST:
    def __init__(self):
        self.values = []
    
    # def __repr__(self):
    #     self.sorted = []
    #     self.get_inorder(self.root)
    #     return str(self.sorted)
    # 
    # def get_inorder(self, node):
    #     if node:
    #         for c in node.values:
    #             self.get_inorder(c)
    #         self.sorted.append(str(node.values))
            
    def add(self, parts):
        print(parts)
        self.add_node(self, self.values, parts, 0)
            
    def add_node(self, parent, values, parts, i):
        if len(parts) == i:
            return
        if not values:
            node = Node(parent, parts[i])
            values.append(node)
            print("node append: ", parts[i])
            self.add_node(node, node.values, parts, i + 1)
            return
        lo = 0
        hi = len(values)
        while lo < hi:
            mid = (lo + hi) // 2
            if values[mid].value < parts[i]: 
                lo = mid + 1
            elif values[mid].value == parts[i]:
                self.add_node(values[mid], values[mid].values, parts, i + 1)
                return
            else:
                hi = mid
        print("node insert: ", parts[i])
        node = Node(parent, parts[i])
        values.insert(lo, node)
        self.add_node(node, node.values, parts, i + 1)
    def print_tree(self):
        self.print_t(None, self.values)
        
    def print_t(self, value, values):
        print(value)
        for v in values:
            self.print_t(v.value, v.values)
        
bst = BST()        
n = int(sys.stdin.readline().strip())
start_domain = None
for i in range(n):
    line = sys.stdin.readline().rstrip()
    if line[0] != ' ':
        start_domain, priority, domain = line.split()
    else:
        priority, domain = line.split();
    name_parts = start_domain.split('.')
    bst.add(name_parts[::-1])
bst.print_tree()
while True:
    line = sys.stdin.readline().strip()
    command = line[0]
    if command == 'A':
        print('A')
    elif command == 'U':
        print('U')
    elif command == 'D':
        print('D')
    elif command == 'X':
        break
