export const routes = {
    public: {
        HOME: '/',
        ABOUT: '/sobre-nosotros',
        FAQ: '/preguntas-frecuentes',
        BLOG: '/blog',
        REGISTER: '/registro',
        LOGIN: '/inicio-sesion'
    },
    private: {
        PROFILE: '/perfil',
        HABITS: '/habitos'
    }
};

export const links = {
    anchors:[
        { name: 'Inicio', url: routes.public.HOME },
        { name: 'Sobre nosotros', url: routes.public.ABOUT },
        { name: 'Preguntas Frecuentes', url: routes.public.FAQ },
        { name: 'Blog', url: '/contacto' }
    ],
    buttons:[
        { name: 'Registro', url: routes.public.REGISTER },
        { name: 'Iniciar sesión', url: routes.public.LOGIN }
    ]
};