from Universe import Universe
from Planet import Planet
import numpy as np
import time
import matplotlib.pyplot as plt
import matplotlib.animation as animation
from mpl_toolkits.mplot3d import Axes3D

class Universe:
    def __init__(self, bodies):
        self.bodies = bodies
        self.sun = Planet(3, 0, np.zeros(3))

if __name__=='__main__':
    plt.style.use('dark_background')
    fig = plt.figure(figsize=[6, 6])
    ax = plt.axes([0., 0., 1., 1.], xlim=(-1.8, 1.8), ylim=(-1.8, 1.8))
    ax.set_aspect('equal')
    ax.axis('off')
    planet1 = Planet(1, 0, np.array([5,0,0]))


